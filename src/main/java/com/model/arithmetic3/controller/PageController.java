package com.model.arithmetic3.controller;

import com.model.arithmetic3.entity.FileLogVO;
import com.model.arithmetic3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/15 0015 下午 19:43
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/15 0015 下午 19:43
 */
@Controller
public class PageController {

    @Autowired
    private UserService userService;

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

    @GetMapping("/file/log")
    public ModelAndView fileUploadLog(HttpServletRequest request){
        String account = request.getSession().getAttribute("account").toString();
        ModelAndView modelAndView = new ModelAndView("tables-file-upload");
        List<FileLogVO> fileLogVOList =  userService.listFileLog(account);
        modelAndView.addObject("logList", fileLogVOList);
        return modelAndView;
    }
    @GetMapping("/user/logout")
    public ModelAndView logout(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("authentication-login");
        request.getSession().removeAttribute("account");
        return modelAndView;
    }
}
