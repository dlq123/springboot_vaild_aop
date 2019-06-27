package com.example.vaild.aop.constant;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-06-21 17:31
 */
public enum RegexTypeEnum {
    /**
     * none
     */
    NONE,
    /**
     * 特殊字符
     */
    SPECIAL_CHAR,
    /**
     * Ip
     */
    IP,
    /**
     * 时间格式
     */
    IS_DATE,

    /**
     * 是否中文
     */
    IS_CHINESE,
    /**
     * 是否电话号码
     */
    IS_PHONE_NUMBER,
    /**
     * 是否数字
     */
    IS_NUMBER,
    /**
     * 判断几位小数(正数)
     */
    IS_DECIMAL;
}
