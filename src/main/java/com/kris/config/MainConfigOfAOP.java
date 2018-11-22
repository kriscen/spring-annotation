package com.kris.config;

import org.springframework.context.annotation.Configuration;


/**
 * AOP:[动态代理]
        程序运行期间，动态的讲某段代码切入到指定方法指定位置进行运行的编程方式。

    1.导入aop模块:spring aop(spring-aspects)
    2.定义业务逻辑类({MathCalculator})
        在业务逻辑类运行的时候记录日志
    3.定义一个日志切面类(logAspects)
        切面里面的通知方法需要感知业务逻辑类运行环节
        通知方法：
            前置通知(@Before)：logStart,在目标方法运行之前运行
            后置通知(@After)：logEnd，在目标方法运行之后运行
            返回通知(@AfterReturning)：logReturn,在目标方法正常返回之后运行
            异常通知(@AfterThrowing)：logException,在目标方法异常之后运行
            环绕通知(@Around):动态代理，手动推进目标方法运行(joinPoint.procced())

 */
@Configuration
public class MainConfigOfAOP {
}
