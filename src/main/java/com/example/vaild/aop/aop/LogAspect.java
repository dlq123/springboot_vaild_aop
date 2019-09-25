package com.example.vaild.aop.aop;

import com.example.vaild.aop.util.HttpContextUtil;
import com.example.vaild.aop.util.IpUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-06-27 17:48
 */
//@Component
//@Aspect
//@Order(10)//aop执行的顺序
public class LogAspect {

    private final static Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 声明切面 应用在，所有controller包下所有的类
     */
    //@Pointcut("execution ( * cn.cechealth.platform.etl.paas.controller..* (..)) || execution( *  cn.cechealth.platform.etl.paas.service..* (..))) ")
    public void pointcut() {
    }

    //@Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Exception {

        Object object = null;
        //也可使用StopWatch记录时间
        long startTime = System.currentTimeMillis();

        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        Long resultTime = endTime - startTime;
        log.info("startTime : [{}]  endTime: [{}]", startTime, endTime);
        log.info("[{}] 耗费时间 ：[{}]", joinPoint.getTarget().getClass().getName(), resultTime);
        saveSystemLog(joinPoint, resultTime);
        return object;
    }

    private void saveSystemLog(ProceedingJoinPoint joinPoint, Long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获得切入方法
        Method method = signature.getMethod();

        //获取对象的成员注解
//        LogOut logOut = method.getAnnotation(LogOut.class);
//        if (logOut != null) {
//            systemLog.setOperation(logOut.operation().getDesc());
//        }
        //请求方法
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        //请求方法参数值
        Object[] args = joinPoint.getArgs();
        //请求方法参数名称
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

        String[] paramNames = discoverer.getParameterNames(method);
        String params = "";
        if (args != null && paramNames != null) {

            for (int i = 0; i < args.length; i++) {
                params = params + " " + paramNames[i] + ":" + args[i];
            }

        }
        //获取request
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();


        log.info("接口请求信息输出:  请求方法：[{}]  请求参数 [{}]  获取请求方式[{}]  获取请求url [{}] 获取机器ip [{}] 响应时间 [{}ms]",
                className + "." + methodName + "()", params, request.getMethod(), request.getRequestURI(), IpUtils.getIpAddr(request), time);

    }
}
