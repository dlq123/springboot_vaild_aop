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

    boolean nullable() default false;

    //自定义正则表达式

    RegexTypeEnum regexType() default RegexTypeEnum.NONE;

   //参数或者字段描述,这样能够显示友好的异常信息

    String description() default "";

    // RegexType需要参数
    int count() default 0;

    // 自定义抛出的异常输出信息

    String message() default "";

}
