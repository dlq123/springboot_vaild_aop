package com.example.vaild.aop.controller;

import com.example.vaild.aop.model.User;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-06-25 17:57
 */
@RestController
public class DemoController {

    @PutMapping("test")
    public void test(@RequestBody User user){
        System.out.println(user.toString());
    }
}
