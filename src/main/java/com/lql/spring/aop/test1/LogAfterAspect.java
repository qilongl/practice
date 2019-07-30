package com.lql.spring.aop.test1;

import com.lql.util.JsonUtil;
import org.aspectj.lang.annotation.After;
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
public class LogAfterAspect {

    /**
     * 在方法完成后调用通知，无论方法是否执行成功
     */
    @After("AopPointcut.businessService()")
    public void logResult() {
        System.out.println("------@After logAfter---方法执行返还值：");
    }
}
