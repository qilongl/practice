package com.lql.spring.aop.test1;

import com.lql.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by StrangeDragon on 2019/7/24 15:09
 **/
@Aspect
@EnableAspectJAutoProxy
@Component
public class LogAroundAspect {

    /**
     * 注意：ProceedingJoinPoint is only supported for around advice
     *
     * @param joinPoint
     * @return
     */
    @Around("AopPointcut.businessService()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        System.out.println("------@Around logAround---参数：" + Arrays.toString(objects));
        Object result = joinPoint.proceed(objects);
        System.out.println("------@Around logAround---返还结果：" + JsonUtil.beanToJson(result));
        return result;


    }
}
