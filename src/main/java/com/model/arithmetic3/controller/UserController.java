package com.model.arithmetic3.controller;

import com.model.arithmetic3.dao.domain.User;
import com.model.arithmetic3.entity.Result;
import com.model.arithmetic3.entity.UserEditParamVO;
import com.model.arithmetic3.entity.UserVO;
import com.model.arithmetic3.service.UserService;
import com.model.arithmetic3.util.SuanFa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Queue;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/17 0017 下午 17:18
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/17 0017 下午 17:18
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param account
     * @param password
     */
    @PostMapping("/user/register")
    public void register(String account, String password){
        userService.register(account, password);
    }

    @PostMapping("/login")
    public void login(UserVO userVO, HttpServletRequest request){
        userService.login(userVO);
        request.getSession().setAttribute("account", userVO.getAccount());
    }

    /**
     * 编辑用户信息
     */
    @PostMapping("/user/edit")
    public void editInfo(UserEditParamVO userEditParamVO){
        userService.editInfo(userEditParamVO);
    }


    /**
     * 编辑用户信息
     */
    @GetMapping("/user/info")
    public ModelAndView userInfo(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("userInfo");
        User user = userService.getInfo(request.getSession().getAttribute("account").toString());

        modelAndView.addObject("account", user.getAccount());
        modelAndView.addObject("password", user.getPassword());
        modelAndView.addObject("name", user.getName());
        modelAndView.addObject("phone", user.getPhone());

        return modelAndView;
    }

    /**
     * 上传文件
     * 使用算法处理
     * 然后存储数据
     */
    @PostMapping("/data/init")
    public void initData(MultipartFile dataFile, Integer type, HttpServletRequest request){
        try {
            if(type == 1) {
                SuanFa.smoothing(dataFile);
            }else {
                SuanFa.histgram(dataFile);
            }
            userService.uploadFileInfo(request.getSession().getAttribute("account").toString(), dataFile.getOriginalFilename(), type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时获取数据
     */
    @GetMapping("/data/consume")
    public Result consumeData(){
        Queue<Result> resultList = SuanFa.resultList;
        // 消耗一行
        return resultList.poll();
    }

    /**
     * 定时获取数据
     */
    @PostMapping("/data/clear")
    public void clearData(){
        SuanFa.resultList.clear();
    }

}
