//package com.lql.spring.aop;
//
//import com.lql.ext.http.HttpRequest;
//import com.lql.util.JsonUtil;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by StrangeDragon on 2019/7/9 15:52
// **/
//@Aspect
//@Component
//public class BusinessAspect {
//    private Logger logger = LoggerFactory.getLogger(BusinessAspect.class);
//
//    @Autowired
//    HttpServletRequest request;
//
//    //    @Pointcut("within(@org.springframework.stereotype.Controller *)" +
////            "||" +
////            "within(@org.springframework.stereotype.Controller *) +" +
////            "execution(* *(..))")
//    @Pointcut("execution(public * com.lql.db.redis.controller.*.*(..))")
//    public void BusinessController() {
//    }
//
//    @Around("BusinessController()")
//    public Object BusinessController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        logger.info("--------BusinessController PointCut Start--------");
//        String requestUri = request.getRequestURI();
//        logger.info("--------requestUri:{}", requestUri);
//        Object[] objects = proceedingJoinPoint.getArgs();
//        Object ret = proceedingJoinPoint.proceed(objects);
//        logger.info("--------{}",ret);
//        return ret;
//    }
//
//    @Pointcut("execution(public * com.lql.db.redis.controller.*.*(..))")
//    public void LogAspect() {
//    }
//
////    @Before("LogAspect()")
////    public void doBefore(JoinPoint joinPoint) {
////        System.out.println("doBefore");
////    }
//
////    @After("LogAspect()")
////    public void doAfter(JoinPoint joinPoint) {
////        System.out.println("doAfter");
////    }
//
//    @AfterReturning(returning = "object", pointcut = "LogAspect()")
//    public Object doAfterReturning(JoinPoint joinPoint, Object object) {
//        logger.info("--------------Result:{}", JsonUtil.beanToJson(object));
//        return object;
//    }
//
//    @AfterThrowing("LogAspect()")
//    public void deAfterThrowing(JoinPoint joinPoint) {
//        System.out.println("deAfterThrowing");
//    }
//
////    @Around("LogAspect()")
////    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable {
////        System.out.println("deAround");
////        return joinPoint.proceed();
////    }
//}
