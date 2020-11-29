package com.qiuyi.springdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Order {

    @RequestMapping("hello")
    public String hello(){
        return "hello world!";
    }
}
