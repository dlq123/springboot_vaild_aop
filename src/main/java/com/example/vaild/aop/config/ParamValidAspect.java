package com.example.vaild.aop.config;

import com.example.vaild.aop.annotation.Validate;
import com.example.vaild.aop.constant.RegexErrorEnum;
import com.example.vaild.aop.constant.RegexTypeEnum;
import com.example.vaild.aop.exception.ValidException;
import com.example.vaild.aop.utils.RegexUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.ValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-06-25 15:54
 */
@Component
@Aspect
public class ParamValidAspect {

    private static final Logger log = LoggerFactory.getLogger(ParamValidAspect.class);

    /**
     * 声明切面 应用在，所有controller包下所有的类
     */
    @Pointcut("execution(* com.example.vaild.aop.controller.*.*(..))")
    public void controllerBefore() {
    }

    @Before("controllerBefore()")
    public void checkParameter(JoinPoint joinPoint) throws ValidationException {
        //获得切入方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("args: " + args.toString());
        //获得切入方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        System.out.println("method: " + method.toString());
        //获得所有参数
        Parameter[] parameters = method.getParameters();
        System.out.println("parameters: " + parameters.toString());

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Class<? extends Object> clazz = parameter.getType();
            // 获取该类型声明的成员
            Field[] fields = clazz.getDeclaredFields();
            //  遍历属性
            System.out.println(args[i].toString());
            if(fields != null && fields.length > 0){
                for (Field field : fields) {
                    // 对于private私有化的成员变量，通过setAccessible来修改器访问权限
                    field.setAccessible(true);
                    validate(field, args[i]);
                    // 重新设置会私有权限
                    field.setAccessible(false);
                }
            }
        }

    }
    private static void validate(Field field, Object object) throws ValidException {
        String description;
        Object value = null;
        //获取对象的成员注解
        Validate dv = field.getAnnotation(Validate.class);
        try {
            value = field.get(object);
        } catch (Exception e) {
            throw new ValidException("解析错误");
        }
        if (dv == null) {
            return;
        }
        description = dv.message().equals("") ? field.getName() : dv.description();
        String message = dv.message();
        if (!dv.notNull()) {
            if (value == null || "".equals(value.toString())) {
                throw new ValidException(description + RegexErrorEnum.NONE.getDesc());
            }
        }

        if (value != null) {
            if (dv.regexType() != RegexTypeEnum.NONE) {
                switch (dv.regexType()) {
                    case NONE:
                        break;
                    case SPECIAL_CHAR:
                        if (RegexUtils.containSpecialChar(value.toString())) {
                            throwException(description, message, RegexErrorEnum.SPECIAL_CHAR.getDesc());
                        }
                        break;
                    case IP:
                        if (!RegexUtils.ipTest(value.toString())) {
                            throwException(description, message, RegexErrorEnum.IP.getDesc());
                        }
                        break;
                    case IS_DATE:
                        if (!RegexUtils.isValidDate(value.toString())) {
                            throwException(description, message, RegexErrorEnum.IS_DATE.getDesc());
                        }
                        break;
                    case IS_CHINESE:
                        if (RegexUtils.isChinese(value.toString())) {
                            throwException(description, message, RegexErrorEnum.IS_CHINESE.getDesc());
                        }
                        break;
                    case IS_NUMBER:
                        if (!RegexUtils.isNumber(value.toString())) {
                            throwException(description, message, RegexErrorEnum.IS_NUMBER.getDesc());
                        }
                        break;
                    case IS_PHONE_NUMBER:
                        if (!RegexUtils.isPhoneNumber(value.toString())) {
                            throwException(description, message, RegexErrorEnum.IS_PHONE_NUMBER.getDesc());
                        }
                        break;
                    case IS_DECIMAL:
                        if (!RegexUtils.isDecimal(value.toString(), dv.count())) {
                            throwException(description, message, RegexErrorEnum.IS_DECIMAL.getDesc());
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void throwException(String description, String message, String defaultString) throws ValidException {
        if (!StringUtils.isEmpty(message)) {
            throw new ValidException(description + message);
        } else {
            throw new ValidException(description + defaultString);
        }
    }


    @Override
    public String toString() {
        Class<?> c = this.getClass();
        StringBuilder sbuilder = new StringBuilder();
        Field[] fields = c.getDeclaredFields();
        sbuilder.append(c.getName() + "[");
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            fields[i].getName();
            try {
                if (i == fields.length - 1) {
                    sbuilder.append(fields[i].getName() + ":" + fields[i].get(this).toString());
                } else {
                    sbuilder.append(fields[i].getName() + ":" + fields[i].get(this).toString() + ",");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sbuilder.append("]");
        return sbuilder.toString();
    }
}
