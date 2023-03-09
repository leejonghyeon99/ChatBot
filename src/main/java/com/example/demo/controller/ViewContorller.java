package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewContorller {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/user")
    public String user(){
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
