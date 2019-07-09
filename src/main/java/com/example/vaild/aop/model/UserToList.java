package com.example.vaild.aop.model;

import com.example.vaild.aop.annotation.Validate;
import lombok.Data;

import java.util.List;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-07-04 11:42
 */
@Data
public class UserToList {
    @Validate
    private  List<User> users;

}
