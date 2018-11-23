package com.kris.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 切面类
 * @Aspect 告诉spring这是一个切面类
 */

@Aspect
public class LogAspects {
    //@brfore 在目标方法之前切入：切入点表达式
    /**
     * 抽取公共的切入点表达式
     * 1.本类引用
     * 2.其他切面引用
     */
    @Pointcut("execution(public int com.kris.aop.MathCalculator.*(..))")
    public void pointCut(){}
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"运行。。参数列表:"+ Arrays.asList(joinPoint.getArgs()));
    }
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"结束");
    }
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logRetuen(JoinPoint joinPoint,Object result){
        System.out.println(joinPoint.getSignature().getName()+"正常返回。。运行结果:{"+result+"}");
    }
    //JoinPoint 必须在参数列表第一个
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(joinPoint.getSignature().getName()+"异常。。异常信息：{"+exception.getMessage()+"}");
    }
}
