package com.example.vaild.aop.controller;

import com.example.vaild.aop.annotation.Validate;
import com.example.vaild.aop.model.User;
import com.example.vaild.aop.model.UserListList;
import com.example.vaild.aop.model.UserToList;
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
    public void test(@RequestBody @Validate User user){
        System.out.println(user.toString());
    }

    @PutMapping("testList")
    public void testList(@RequestBody @Validate UserToList userToList){
        System.out.println(userToList.toString());
    }

    @PutMapping("testListList")
    public void testListList(@RequestBody @Validate   UserListList userListList){
        System.out.println(userListList.toString());
    }
}
