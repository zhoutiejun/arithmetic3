package com.model.arithmetic3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/15 0015 下午 19:43
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/15 0015 下午 19:43
 */
@Controller
public class PageController {

    @RequestMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("index");
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("authentication-login");
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("authentication-register");
    }
}
