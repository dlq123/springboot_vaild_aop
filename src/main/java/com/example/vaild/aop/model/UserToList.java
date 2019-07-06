package com.example.vaild.aop.model;

import com.example.vaild.aop.annotation.Validate;
import lombok.Data;

import java.util.List;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-07-04 11:42
 */

public class UserToList {
    @Validate
    private  List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
