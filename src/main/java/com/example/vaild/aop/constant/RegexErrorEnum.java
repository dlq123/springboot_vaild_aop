package com.example.vaild.aop.constant;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-06-26 15:20
 */
public enum RegexErrorEnum {
    /**
     * null
     */
    NO_NULL("不能NULL"),
    /**
     * ""
     */
    NO_STRING("不能空字符串"),
    /**
     * 特殊字符
     */
    SPECIAL_CHAR("不能含有特殊字符"),
    /**
     * Ip
     */
    IP("地址格式不正确"),
    /**
     * 时间格式
     */
    IS_DATE("时间格式不正确"),

    /**
     * 是否中文
     */
    IS_CHINESE("不能含有中文字"),
    /**
     * 是否电话号码
     */
    IS_PHONE_NUMBER("电话号码输入有误"),
    /**
     * 是否数字
     */
    IS_NUMBER("不是数字"),
    /**
     * 判断几位小数(正数)
     */
    IS_DECIMAL("小数位有问题"),

    /**
     * 最大值
     */
    MAX("不能超过最大值"),
    /**
     * 最小值
     */
    MIN("不能低于最小值"),

    /**
     * 最大长度
     */
    MAX_LENGTH("不能超过最大长度"),
    /**
     * 最小长度
     */
    MIN_LENGTH("不能低于最小长度");

    private String desc;

    RegexErrorEnum(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }

}
