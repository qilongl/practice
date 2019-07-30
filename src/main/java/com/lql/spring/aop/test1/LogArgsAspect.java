package com.lql.spring.aop.test1;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by StrangeDragon on 2019/7/24 10:00
 * <p>
 * 单元测试时记得加上注解  @EnableAspectJAutoProxy  ，该注解的意思是开启AOP
 **/
@Aspect
@EnableAspectJAutoProxy
@Component
public class LogArgsAspect {

    /**
     * @param joinpoint
     * @Before 通常用来用日志记录被拦截的方法的入参情况
     * 要获取参数，则在@Before注解的方法上 JoinPoint 类型的参数即可
     * 注意：
     * 第一，必须放置在第一个参数上
     * 第二，如果是 @Around，我们通常会使用其子类 ProceedingJoinPoint，因为它有 procceed()/procceed(args[]) 方法
     */
    @Before("AopPointcut.businessService()")
    public void logArgs(JoinPoint joinpoint) {
//        String requestUri = request.getRequestURI();
//        System.out.println("------logArgs---请求uri：" + requestUri);
        System.out.println("------@Before logArgs---方法名：" + joinpoint.getSignature());
        System.out.println("------@Before logArgs---方法执行前参数：" + Arrays.toString(joinpoint.getArgs()));
    }
}
