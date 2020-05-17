package com.model.arithmetic3.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/17 0017 下午 17:18
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/17 0017 下午 17:18
 */
@RestController
public class UserController {

    @PostMapping("/register")
    public void register(){

    }

    @PostMapping("/login")
    public void login(String account, String password){
        System.out.println("aa");
    }

    /**
     * 编辑用户信息
     */
    @PostMapping("/user/edit")
    public void editInfo(){

    }
}
