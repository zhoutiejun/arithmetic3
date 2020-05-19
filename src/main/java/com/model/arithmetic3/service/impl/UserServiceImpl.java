package com.model.arithmetic3.service.impl;

import com.model.arithmetic3.dao.domain.FileLog;
import com.model.arithmetic3.dao.domain.FileLogExample;
import com.model.arithmetic3.dao.domain.User;
import com.model.arithmetic3.dao.domain.UserExample;
import com.model.arithmetic3.dao.mapper.FileLogMapper;
import com.model.arithmetic3.dao.mapper.UserMapper;
import com.model.arithmetic3.entity.FileLogVO;
import com.model.arithmetic3.entity.UserEditParamVO;
import com.model.arithmetic3.entity.UserVO;
import com.model.arithmetic3.service.UserService;
import com.model.arithmetic3.util.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/18 0018 下午 20:44
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/18 0018 下午 20:44
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileLogMapper fileLogMapper;

    @Override
    public void register(String account, String password) {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);

        userMapper.insertSelective(user);
    }

    @Override
    public void login(UserVO userVO) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
            .andAccountEqualTo(userVO.getAccount())
            .andPasswordEqualTo(userVO.getPassword());

        List<User> userList = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(userList)){
            throw new IllegalArgumentException("用户名异常");
        }
    }

    @Override
    public void editInfo(UserEditParamVO userEditParamVO) {
        User user = new User();
        BeanUtils.copyProperties(userEditParamVO, user);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(userEditParamVO.getAccount());
        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    public User getInfo(String account) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountEqualTo(account);

        List<User> userList = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(userList)){
            throw new IllegalArgumentException("用户异常");
        }
        return userList.get(0);
    }

    @Override
    public List<FileLogVO> listFileLog(String account) {
        FileLogExample fileLogExample = new FileLogExample();
        fileLogExample.createCriteria().andAccountEqualTo(account);
        fileLogExample.setOrderByClause(" create_time desc ");

        List<FileLog> fileLogList = fileLogMapper.selectByExample(fileLogExample);
        return transferBO2VO(fileLogList);
    }

    @Override
    public void uploadFileInfo(String account, String originalFilename, Integer type) {
        FileLog fileLog = new FileLog();
        fileLog.setAccount(account);
        fileLog.setFileName(originalFilename);
        fileLog.setType(type);

        fileLogMapper.insertSelective(fileLog);
    }

    private List<FileLogVO> transferBO2VO(List<FileLog> fileLogList) {
        List<FileLogVO> fileLogVOList = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
        for(FileLog fileLog : fileLogList){
            FileLogVO fileLogVO = new FileLogVO();
            BeanUtils.copyProperties(fileLog, fileLogVO);

            fileLogVO.setCreateTime(TimeUtil.getOriginTimeStr(fileLog.getCreateTime()));
            fileLogVOList.add(fileLogVO);
        }
        return fileLogVOList;
    }
}
