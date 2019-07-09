package com.example.vaild.aop.model;

import com.example.vaild.aop.annotation.Validate;
import com.example.vaild.aop.constant.RegexTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-06-25 17:57
 */
@Data
public class User {


    @Validate(regexType = RegexTypeEnum.IS_NUMBER)
    private Integer id;
    @Validate(regexType = RegexTypeEnum.SPECIAL_CHAR)
    private  String  name;
    @Validate(regexType = RegexTypeEnum.IS_CHINESE)
    private  String  password;
    @Validate(max = 100,min = 10)
    private  int num;

    @Validate(maxLength = 6,minLength = 3)
    private String ok;

    List<Integer> ids;
}
