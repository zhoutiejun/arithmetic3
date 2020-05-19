package com.model.arithmetic3.service;

import com.model.arithmetic3.dao.domain.User;
import com.model.arithmetic3.entity.FileLogVO;
import com.model.arithmetic3.entity.UserEditParamVO;
import com.model.arithmetic3.entity.UserVO;

import java.util.List;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/18 0018 下午 20:44
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/18 0018 下午 20:44
 */
public interface UserService {
    /**
     * 注册
     * @param account
     * @param password
     */
    void register(String account, String password);

    /**
     * 登录
     * @param userVO
     */
    void login(UserVO userVO);

    /**
     * 修改用户数据
     * @param userEditParamVO
     */
    void editInfo(UserEditParamVO userEditParamVO);

    /**
     * 获取用户信息
     * @param account
     * @return
     */
    User getInfo(String account);

    /**
     * 获取上传文件你是记录
     * @param account
     * @return
     */
    List<FileLogVO> listFileLog(String account);

    /**
     * 上传文件
     * @param account
     * @param originalFilename
     * @param type
     */
    void uploadFileInfo(String account, String originalFilename, Integer type);
}
