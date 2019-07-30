package com.lql.spring.aop.test1;

import com.lql.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by StrangeDragon on 2019/7/24 11:30
 **/
@Aspect
@EnableAspectJAutoProxy
@Component
public class LogAfterThrowAspect {

    /**
     * 在方法抛出异常后调用通知
     */
    @AfterThrowing(pointcut = "AopPointcut.businessService()",throwing = "e")
    public void logResult(Throwable e) {
        System.err.println("------@AfterThrowing logAfterThrow---异常信息："+e.getMessage());
    }
}
