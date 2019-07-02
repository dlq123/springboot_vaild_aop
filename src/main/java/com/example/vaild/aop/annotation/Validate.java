package com.example.vaild.aop.annotation;

import com.example.vaild.aop.constant.RegexTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-06-21 17:36
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Validate {

     //是否可以为空
    boolean notNull() default false;

    //最大值
    long max() default 0;

    //最小值
    long min() default 0;

    //最大长度
    long maxLength() default 0;

    //最小长度
    long minLength() default 0;

    //自定义正则表达式

    RegexTypeEnum regexType() default RegexTypeEnum.NONE;

    // RegexType需要参数
    int count() default 0;

    // 自定义抛出的异常输出信息

    String message() default "";

}
