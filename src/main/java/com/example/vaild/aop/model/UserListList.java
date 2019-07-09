package com.example.vaild.aop.model;

import com.example.vaild.aop.annotation.Validate;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-07-07 14:52
 */
@Data
public class UserListList {

    List<List<String>> stringLists;

    List<String> strings;

    List<List<Integer>> intLists;

    List<Integer> ints;

    List<Map<String,String>> mapList;

    @Validate
    List<UserToList> listList;

}
