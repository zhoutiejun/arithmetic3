package com.model.arithmetic3.util;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWException;
import GaussFitting2.Class1;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.model.arithmetic3.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import static java.lang.Math.floor;
/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/18 0018 下午 19:20
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/18 0018 下午 19:20
 */
public class SuanFa {

    public static Queue<Result> resultList = new ConcurrentLinkedQueue<>();

    public static void testsmoothing(MultipartFile file){
        //每一秒钟存储一次数据
        for(int i = 0 ; i< 10 ; i++){

            Result result = new Result();
            List readList = new ArrayList();
            List blueList = new ArrayList();
            for(int j = 0 ; j< 10 ; j++){
                readList.add(new Random().nextInt(10));
                blueList.add(new Random().nextInt(20));
            }
            result.setBlueLine(readList);
            result.setReadLine(blueList);
            result.setPoint(new Random().nextInt(40));
            resultList.add(result);
        }
    }

    public static void smoothing(MultipartFile file) throws IOException {
        float[][] array = readFile(file);
        //测试读取数据有没有错误

//        for(int i = 0;i<array.length;i++)
//        {
//            for (int j = 0;j<array[0].length;j++) {
//                System.out.print(array[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println(array.length+"  "+array[0].length);那就
        int wsize = 50;
        int segNum = (int) Math.floor(array[0].length/wsize);
        float alpha = (float) 0.9;
        float expValue = (float) 2.2;
        ArrayList<Float> oneData = new ArrayList<>();
        ArrayList<Float> reMeanData = new ArrayList<>();
        ArrayList<Float> threshold = new ArrayList<>();
        ArrayList<Float> dataType = new ArrayList<>();
        ArrayList<Float> lowSegma = new ArrayList<>();
        for(int i = 0 ;i<segNum*wsize;i++)           //将要用的集合全部付初值(数组第一行数据)  长度为segNum*wsize
        {
            oneData.add(array[0][i]);
            reMeanData.add(array[0][i]);
            threshold.add(array[0][i]);
            dataType.add(array[0][i]);
            lowSegma.add(array[0][i]);
        }
        for(int i =1;i<array.length;i++)      //开始每行的循环   每次循环得到一次蓝线和红线
        {
            for(int j=0;j<segNum*wsize;j++)
            {
                oneData.set(j,array[i][j]);        //将oneData中的数据修改为当前行的数据
            }
            for (int j=0;j<reMeanData.size();j++)
            {
                reMeanData.set(j,alpha* reMeanData.get(j) +(1-alpha)*oneData.get(j));   //将信号预处理 得到蓝线  降低变化幅度
            }
            for (int j = 0 ;j<segNum;j++)   //确定该行datatype的值   即信号点
            {
                int pos1 = j*wsize;
                float average =0;
                for (int k =j*wsize;k<(j+1)*wsize;k++)
                {
                    average +=reMeanData.get(k);
                }
                average = average/wsize;
                for (int k=j*wsize;k<(j+1)*wsize;k++)
                {
                    if(reMeanData.get(k)>average+expValue){
                        dataType.set(k, (float) 1.0);
                    }
                    else {
                        dataType.set(k, (float) 0.0);
                    }
                }
            }
            for (int j=0;j<reMeanData.size();j++)  //确定红线即分界噪音线的值
            {
                int zp1 = -1;
                int zp2 = -1;
                float p = (float) 0.0;
                if (oneData.get(j)<reMeanData.get(j)){
                    p = (float) 0.95;
                }
                else {
                    p= (float) 0.80;
                }
                if (dataType.get(j)==0.0){
                    threshold.set(j,p*reMeanData.get(j)+(1-p)*oneData.get(j)+5);
                }
                else {
                    for(int k=j;k>0;k--)
                    {
                        if(dataType.get(k)==0.0){
                            zp1 = k;
                            break;
                        }
                    }
                    for(int k=j;k<reMeanData.size();k++)
                    {
                        if(dataType.get(k)==0){
                            zp2 = k;
                            break;
                        }
                    }
                    if(zp1 == -1 && zp2 != -1){
                        zp1 = zp2;
                    }
                    if (zp2 == -1 && zp1!= -1){
                        zp2 = zp1;
                    }
                    if(zp1 == -1 && zp2 == -1){
                        zp1 = 1;
                        zp2 = reMeanData.size()-1;
                    }
                    threshold.set(j,p*(reMeanData.get(zp1)+reMeanData.get(zp2))/2+(1-p)*oneData.get(j)+5);
                }
            }
            //到这里有两个集合分别报存蓝线（reMeanData）和红线(threshold)
            System.out.println("蓝线："+reMeanData);                      //蓝线 即信号
            System.out.println("红线："+threshold);                       //红线  即分割线
            Integer signalNum = SignalNum(reMeanData,threshold);
            System.out.println("这一帧信号个数："+signalNum);    //信号个数
            Result result = new Result();
            result.setBlueLine(reMeanData);
            result.setReadLine(threshold);
            result.setPoint(signalNum);
        }
    }          // 算法1 平滑滤波算法

    public static void histgram(MultipartFile file) throws IOException, MWException {
        float[][] array = readFile(file);
        //测试读取数据有没有错误

//        for(int i = 0;i<array.length;i++)
//        {
//            for (int j = 0;j<array[0].length;j++) {
//                System.out.print(array[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println(array.length+"  "+array[0].length);那就
        float wsize = 50;
        //     int flag = 0;
        int segNum = (int) floor(array[0].length / wsize);
        float alpha = (float) 0.9;
        ArrayList<Float> oneData = new ArrayList<>();
        ArrayList<Float> reMeanData = new ArrayList<>();
        ArrayList<Float> threshold = new ArrayList<>();
        ArrayList<Float> smOneData = new ArrayList<>();
        ArrayList<Float> lowSegma = new ArrayList<>();
        ArrayList<Float> AmenData = new ArrayList<>();
        ArrayList<Integer> hist = new ArrayList<>();
        for (int i = 0; i < segNum * wsize; i++)           //将要用的集合全部付初值(数组第一行数据)  长度为segNum*wsize
        {
            oneData.add(array[0][i]);
            reMeanData.add(array[0][i]);
            threshold.add(array[0][i]);
            smOneData.add(array[0][i]);
            lowSegma.add(array[0][i]);
            // winData.add(array[0][i]);
            AmenData.add(array[0][i]);
        }
        for (int i = 0; i < 90; i++) {
            hist.add(0);
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < reMeanData.size(); j++) {
                oneData.set(j, array[i][j]);
            }
            for (int j = 0; j < reMeanData.size(); j++) {
                reMeanData.set(j, alpha * reMeanData.get(j) + (1 - alpha) * oneData.get(j));//蓝线处理完
            }
            //   System.out.println(reMeanData);
            for (int j = 0; j < reMeanData.size(); j++) {
                smOneData.set(j, reMeanData.get(j));
                //   winData.set(j,reMeanData.get(j));
            }
            //  System.out.println(smOneData);
            //   Collections.sort(winData);
            ArrayList<Float> windData = new ArrayList<>();
            for (int j = 0; j < wsize; j++) {
                windData.add(reMeanData.get(j));
            }
            for (int j = 0; j < reMeanData.size() - wsize; j++) {
                float average = 0;
                for (int k = 0; k < wsize; k++) {
                    windData.set(k, reMeanData.get(j + k));
                }
                //  System.out.println(windData);
                Collections.sort(windData);
                //  System.out.println(windData);
                for (int k = 0; k < floor(wsize / 3); k++) {
                    average += windData.get(k);
                }
                average = average / (wsize / 3);
                for (int k = j; k < j + wsize + 1; k++) {
                    lowSegma.set(k, average);
                }
            }
            //System.out.println(lowSegma);


            for (int j = 0; j < reMeanData.size(); j++) {
                smOneData.set(j, smOneData.get(j) - lowSegma.get(j));
            }

            float minv = Collections.min(smOneData);
            float maxv = Collections.max(smOneData);
            for (int j = 0; j < reMeanData.size(); j++) {
                AmenData.set(j, smOneData.get(j));
            }

            for (int j = 0; j < reMeanData.size(); j++) {
                AmenData.set(j, AmenData.get(j) - Collections.min(AmenData));
            }

            for (int j = 0; j < reMeanData.size(); j++) {
                AmenData.set(j, AmenData.get(j) / Collections.max(AmenData));
            }

            for (int j = 0; j < reMeanData.size(); j++) {
                AmenData.set(j, AmenData.get(j) * 90);
            }
            float range;
            range = (Collections.max(AmenData) - Collections.min(AmenData)) / 90;
            for (int j = 0; j < 90; j++) {
                int num = 0;
                for (int k = 0; k < AmenData.size(); k++) {
                    while(AmenData.get(k) >= j * range && AmenData.get(k) < (j + 1) * range)
                    {
                        num = num + 1;
                        break;
                    }
                }
                hist.set(j, num);
            }
            //直方图仿真

            int k = 0;

            for (int j = 0; j < 90; j++) {
                if (hist.get(j) >= Collections.max(hist)) {
                    k = j;
                }
            }
            //    System.out.println(k);
            if (k <= 35) {
                ArrayList<Integer> x = new ArrayList<>();
                for (int m = 0; m < 20; m++) {
                    x.add((k + m));
                }
                ArrayList<Integer> y = new ArrayList<>();
                for (int m = 0; m < 20; m++) {
                    int e = x.get(m);
                    y.add(hist.get(e));
                }
                ArrayList<Integer> ty = new ArrayList<>();
                for (int m = 0; m < y.size(); m++) {
                    ty.add(y.get(y.size() - m - 1));
                }
                ArrayList<Integer> x1 = new ArrayList<>();
                ArrayList<Integer> y1 = new ArrayList<>();
                for (int m = 0; m < 40; m++) {
                    x1.add(m + 1);
                }
                y1.addAll(ty);
                y1.addAll(y);
                for (int m = 0; m < x1.size(); m++) {
                    if (y1.get(m) == 0) {
                        y1.remove(m);
                        x1.remove(m);
                    }
                }
                ArrayList<Float> x2 = new ArrayList<>();
                ArrayList<Float> y2 = new ArrayList<>();
                for (int m = 0; m < x1.size(); m++) {
                    x2.add(Float.valueOf(x1.get(m)));
                    y2.add(Float.valueOf(y1.get(m)));
                }
                double sigma;                                       //调用高斯函数
                sigma = gs(x2, y2)[1];
                for (int m = 0; m < threshold.size(); m++) {
                    threshold.set(m, ((k + 3 * (float) sigma) * (maxv - minv) / 90 + minv));
                }
            } else if (k > 70) {
                for (int m = 0; m < threshold.size(); m++) {
                    threshold.set(m, (2 * k - 90) * (maxv - minv) / 90 + minv);
                }
            } else {
                ArrayList<Integer> x1 = new ArrayList<>();
                ArrayList<Integer> y1 = new ArrayList<>();
                for (int m = 0; m < 40; m++) {
                    x1.set(m, k + m - 20);
                }
                for (int m = 0; m < 40; m++) {
                    y1.set(m, hist.get(k - 20 + m));
                }
                for (int m = 0; m < x1.size(); m++) {
                    if (y1.get(m) == 0) {
                        y1.remove(m);
                        x1.remove(m);
                    }
                }
                ArrayList<Float> x2 = new ArrayList<>();
                ArrayList<Float> y2 = new ArrayList<>();
                for (int m = 0; m < x1.size(); m++) {
                    x2.add(Float.valueOf(x1.get(m)));
                    y2.add(Float.valueOf(y1.get(m)));
                }
                //调用高斯函数
                double sigma = gs(x2, y2)[1];
                double u = gs(x2, y2)[0];
                if ((float) sigma < 10) {
                    if (k < 45) {
                        for (int n = 0; n < threshold.size(); n++) {
                            threshold.set(n, (float) ((k + 2.3 * (float) sigma) * (maxv - minv) / 90 + minv));
                        }
                    } else {
                        for (int n = 0; n < threshold.size(); n++) {
                            threshold.set(n, (float) ((k - 2.3 * (float) sigma) * (maxv - minv) / 90 + minv));
                        }
                    }
                } else {

                    for (int n = 0; n < threshold.size(); n++) {
                        threshold.set(n, ((float) u + 3 * (float) sigma) * (maxv - minv) / 90 + minv);
                    }
                }
            }
            for (int h = 0; h < threshold.size(); h++) {
                threshold.set(h, threshold.get(h) + lowSegma.get(h));
            }
            // System.out.println(flag);
            //  writeData("reMeanData.txt",reMeanData);
            //  writeData("threshold.txt",threshold);
            System.out.println("蓝线："+reMeanData);                      //蓝线 即信号线
            System.out.println("红线："+threshold);                       //红线  即分割线
            Integer signalNum = SignalNum(reMeanData,threshold);
            System.out.println("这一帧信号个数："+signalNum);    //信号个数
            Result result = new Result();
            result.setBlueLine(reMeanData);
            result.setReadLine(threshold);
            result.setPoint(signalNum);
        }
    }  //算法2  直方图算法

    public static int SignalNum(ArrayList<Float> list1,ArrayList<Float> list2){
        int signum = 0;
        for(int i = 1;i<list1.size()-2;i++)
        {
            if(list1.get(i-1)<list2.get(i-1)&&list1.get(i)>=list2.get(i))
            {
                signum = signum+1;
                i=i+2;
            }
        }
        return signum;
    }  //求信号点数
    public static void writeData(String fileName,ArrayList<Float> list) throws IOException {
        //创建输出缓冲流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        Float s ;
        StringBuilder sb = new StringBuilder();
        for(int x=0; x<list.size(); x++) {
            s = list.get(x);
            sb.append(s).append(",");
        }
        bw.write(sb.toString());
        bw.newLine();
        bw.flush();
        bw.close();
    }    //写入文件（测试时用的）




    public static double[] gs(ArrayList<Float> x, ArrayList<Float> y) throws MWException {
        Double[] x1 = new Double[x.size()];
        Double[] y1 = new Double[y.size()];
        Class1 gs = new Class1();
        for (int i = 0; i < x.size(); i++) {
            x1[i] = Double.valueOf(x.get(i));
            y1[i] = Math.log(y.get(i));
        }
        Object[] a = gs.GaussFitting2(2, x1, y1);
        MWNumericArray output_u = null;
        output_u = (MWNumericArray) a[0];
        double u = output_u.getDouble(1);
        MWNumericArray output_sigma = null;
        output_sigma = (MWNumericArray) a[1];
        double sigma = output_sigma.getDouble(1);
        double[] b = new double[2];
        b[0] = u;
        b[1] = sigma;

        MWArray.disposeArray(output_u);
        MWArray.disposeArray(output_sigma);
        MWArray.disposeArray(u);
        MWArray.disposeArray(sigma);
        return b;
    } //高斯函数处理变量 输出均值和方差

    public static float[][] readFile(MultipartFile file) throws IOException {
        BufferedReader bu = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        int i = 0;
        int m = 0;
        while ((line = bu.readLine()) != null) {
            i++;
        }
        String[] array = new String[i];
        bu.close();
        BufferedReader bu2 = new BufferedReader(new InputStreamReader(file.getInputStream()));
        while ((line = bu2.readLine()) != null) {
            array[m] = line;
            m++;
        }
        String[] split = array[0].split(",");
        String[][] array1 = new String[array.length][split.length];
        for (int j = 0; j < array.length; j++) {
            array1[j] = array[j].split(",");
        }
        bu2.close();
        float[][] array0 = new float[array1.length][array1[0].length];
        for (int j = 0; j < array1.length; j++) {
            for (int k = 0; k < array1[0].length; k++) {
                array0[j][k] = Float.parseFloat(array1[j][k]);
            }
        }
        return array0;
    }  //读文件

    public static void main(String[] args) throws IOException, MWException {
        //String filename = "pscan_88_108_50.txt";
        // String filename = "pscan_88_137_50.txt";
        // String filename = "pscan_137_167_25.txt";
        String filename = "pscan_400_430_25.txt";                   //4个文件
        System.out.println("请选择你要进行的的算法：");
        System.out.println("输入1 平滑滤波算法。。。。");
        System.out.println("输入2 直方图算法。。。。。");
        Scanner sc = new Scanner(System.in);
        String choiceString = sc.nextLine();
        if(choiceString=="1")
        {
//            smoothing(filename);
        }
        else{
//            histgram(filename);
        }
    }
}
