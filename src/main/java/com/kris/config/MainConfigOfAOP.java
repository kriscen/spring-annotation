package com.kris.config;

import com.kris.aop.LogAspects;
import com.kris.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


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
            后置通知(@After)：logEnd，在目标方法运行之后运行,无论方法是否异常结束
            返回通知(@AfterReturning)：logReturn,在目标方法正常返回之后运行
            异常通知(@AfterThrowing)：logException,在目标方法异常之后运行
            环绕通知(@Around):动态代理，手动推进目标方法运行(joinPoint.procced())
    4.给切面类的目标方法标注何时何地运行(通知注解)
    5.将切面类和业务逻辑类(目标方法所在类)加入到容器中
    6.告诉spring那个类是切面类,切面类加注解
    7.给配置类加@EnableAspectJAutoProxy，启用基于注解的aop模式
    EnableXXX注解，相当于sprnig开始很多功能

    总结：
        1.讲业务逻辑组件和切面类都加入到容器中，告诉spring哪个是切面类@Aspect
        2.在切面类上的每一个通知方法都标注上通知注解，告诉spring何时何地运行(切入点表达式)
        3.开启基于注解的AOP模式

    AOP原理：
        @EnableAspectJAutoProxy
    1.@EnableAspectJAutoProxy?
            @Import(AspectJAutoProxyRegistrar.class)
            class AspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar
        利用AspectJAutoProxyRegistrar自定义注入组件
            internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator

        AnnotationAwareAspectJAutoProxyCreator继承关系
        ->AspectJAwareAdvisorAutoProxyCreator
            ->AbstractAdvisorAutoProxyCreator
                ->AbstractAutoProxyCreator
                    ->ProxyProcessorSupport
                        implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
            SmartInstantiationAwareBeanPostProcessor:后置处理器，在bean初始化前后做事情
            BeanFactoryAware:Aware接口实现类，自动装配

    创建注册流程AnnotationAwareAspectJAutoProxyCreator：
        1)、传入配置类，创建ioc容器
        2)、注册配置类、调用refresh()刷新容器
        3)、registerBeanPostProcessors(beanFactory);注册bean的后置处理器方便拦截bean的创建
            1.org.springframework.context.support.PostProcessorRegistrationDelegate#registerBeanPostProcessors(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, org.springframework.context.support.AbstractApplicationContext)
            先获取ioc容器已定义了的需要创建对象的所有BeanPostProcessor
            2.给容器中加别的BeanPostProcess
            3.优先注册实现了PriorityOrdered接口BeanPostProcess
            4.再注册实现了Ordered接口的BeanPostProcess
            5.注册没实现优先级接口的BeanPostProcess
            6.注册BeanPostProcess，实际上就是创建BeanPostProcess对象保存在容器中
                创建internalAutoProxyCreator->AnnotationAwareAspectJAutoProxyCreator
                1.创建bean实例
                2.populateBean,给bean的属性赋值
                3.initializeBean,初始化bean
                    1.invokeAwareMethods，处理aware接口的方法回调
                    2.applyBeanPostProcessorsBeforeInitialization,引用后置处理器的PostProcessorsBeforeInitialization
                    3.invokeInitMethods,执行自定义初始化方法
                    4.applyBeanPostProcessorsAfterInitialization，执行后置处理器的PostProcessorsAfterInitialization
                4.beanPostProecssor(AnnotationAwareAspectJAutoProxyCreator)
            7.把beanPostProcessor注册到beanFactory中
                beanFactory.addBeanPostProcessor();
        4)finishBeanFactoryInitialization，完成beanFactory初始化工作，创建剩下的单实例bean
            1.遍历获取容器中所有的bean，依次创建对象getBean(beanName)
                getBean()->doGetBean()->getSingleton()
            2.创建bean:
                [
                    不同处理器：
                        在初始化前后applyBeanPostProcessorsAfterInitialization
                        在创建前后applyBeanPostProcessorsBeforeInstantiaion
                ]
                1.先从缓存中获取当前bean，如果有就说明之前被创建了
                    创建的bean都会被缓存起来
                2.createBean(),创建bean
                    1.resolveBeforeInstantiation,解析
                        希望后置处理器返回一个代理对象，如果有就使用，不能就继续
                        后置处理器
                            1.尝试返回对象
                                applyBeanPostProcessorsBeforeInstantiaion
                                拿到所有后置处理器，如果是指定类型处理器就执行postProcessBeforeInstantiaion


                    2.doCreateBean()真正创建一个bean实例，和3.6流程一样





 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAOP {
    //切面类加到容器中
    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
