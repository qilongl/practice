package com.lql.spring.aop.test1;

import com.lql.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by StrangeDragon on 2019/7/24 11:30
 **/
@Aspect
@EnableAspectJAutoProxy
@Component
public class LogResultAspect {

    /**
     * ProceedingJoinPoint 只支持用于@Around 注解
     * @param object
     */
    @AfterReturning(pointcut = "AopPointcut.businessService()", returning = "object")
    public void logResult(Object object) {
        System.out.println("------@AfterReturning logResult---方法执行返还值：" + JsonUtil.beanToJson(object));
    }
}
