package com.lzj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by li on 17-7-14.
 *
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin/admin";
    }
    @RequestMapping("/user")
    public String user(){
        return "user/user";
    }
    //解释：为什么 login.html中表单的action=/login提交不会被这里的login给拦截
    //这是因为表单中的action为post请求，会被security拦截器给拦截，因此走的是security中验证的部分。
    //而这里的指拦截get中的请求
    @RequestMapping("/login")
    public String login(Model model){
        System.out.println("login");
        model.addAttribute("login","welcome login");
        return "login";
    }
    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }

    @RequestMapping("/403")
    public String error403(){
        return "/403";
    }
}
