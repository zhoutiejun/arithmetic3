package com.model.arithmetic3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/15 0015 下午 19:43
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/15 0015 下午 19:43
 */
@Controller
public class PageController {

    @GetMapping("/page/{pageName}")
    public ModelAndView page(@PathVariable String pageName){
        return new ModelAndView(pageName);
    }

    @GetMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("authentication-login");
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("authentication-register");
    }
}
