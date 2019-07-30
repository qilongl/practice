package com.lql.spring.aop.test1;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by StrangeDragon on 2019/7/24 14:52
 **/
public class AopPointcut {
    /**
     * 切点
     * execution ：切点函数，常用的还有 within
     * execution ：正则匹配方法
     * within ：指定所在类或所在包下面的方法
     *
     * 第一个*号：表示返回类型， *号表示所有的类型
     * 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.lql.spring.aop 包、子孙包下所有类的方法。
     * 第二个*号：表示类名，*号表示所有的类
     * *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut("execution(* com.lql.spring.aop..*.*(..))")
    public void businessService() {
    }
}
