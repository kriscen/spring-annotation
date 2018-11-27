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

    AnnotationAwareAspectJAutoProxyCreator[internalAutoProxyCreator]作用
        1.每一个bean创建前需要调用postProcessBeforeInstantiation()
            MathCalculator和LogAspects的创建
            1)判断当前bean是否在advisedBeans中，保存了所有需要增强的bean
            2)判断当前bena是否是基础类型(Advice,Pointcut,Advisor,AopInfrastructureBean)
                            或者是否是切面(Aspect)
            3)判断是否需要跳过
                1)获取后续的增强器(切面里面的通知方法封装成增强器)List<Advisor> candidateAdvisors
                    每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAfvisor
                    判断每一个增强器是否是AspectJPointcutAdvisor类型，返回true
                2)永远返回false
        2.创建对象
        postProcessAfterInitialization:
            return org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#wrapIfNecessary//包装如果需要的情况下
            1)获取当前bean需要的所有增强器
                1.找到能在当前bean使用的增强器(找哪些通知方法需要切入当前bean方法)
                2.获取能在bean使用的增强器
                3.给增强器排序
            2.保存当前bean在advisedBeans中
            3.如果当前bean需要增强，创建当前bena的代理对象
                1.获取所有增强器->通知方法
                2.保存到proxyFactory
                3.创建代理对象:spring自动决定代理对象
                    org.springframework.aop.framework.DefaultAopProxyFactory#createAopProxy(org.springframework.aop.framework.AdvisedSupport)
                        a.JdkDynamicAopProxy,需要实现接口，jdk可以代理
                        b.ObjenesisCglibAopProxy
            4.给容器中返回当前组件使用cglib增强的代理对象
            5.以后容器中获取的就是组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
        3.目标方法执行流程
            容器中保存了组件的代理对象(cglib增强后的对象)，这个对象里面保存了详细信息(比如增强器，目标对象等)
            1)org.springframework.aop.framework.DefaultAopProxyFactory#createAopProxy(org.springframework.aop.framework.AdvisedSupport)
                cglib.intercept(),拦截目标方法的执行
            2).根据proxyFactory对象获取简要执行的拦截器链
            List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
                org.springframework.aop.framework.DefaultAdvisorChainFactory#getInterceptorsAndDynamicInterceptionAdvice(org.springframework.aop.framework.Advised, java.lang.reflect.Method, java.lang.Class)
                1)一个默认的ExposeInvocationInterceptor和是个增强器
                2)遍历每一个增强器，将其转为interceptor
                MethodInterceptor[] interceptors = registry.getInterceptors(advisor);
                3.将增强器转为List<MethodInterceptor>
                    如果是methodInterceptor,直接加入到集合中
                    如果不是，使用advisorAdapter将增强器强转为methodInterceptor
                    转换完成返回MethodInterceptor数组
            3).如果没有拦截器链，直接执行目标方法
                拦截器链->每一个通知方法又被包装为方法拦截器，利用方法拦截器机制执行
            4).如果有就创建CglibMethodInvocation并调用proceed
            new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed()
            5).拦截器链的触发过程
                1.如果没有拦截器执行目标方法，或者拦截器的索引和连接区数组-1大小一样(指定到了最后一个拦截器)执行目标方法啊；
                2.链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完返回以后再执行
                    连接器链的机制，保证通知方法与目标方法的执行顺序

                ExposeInvocaionInterceptor
                            |
                AspectJAfterThrowingAdvice
                            |        ->异常通知
                AfterReturningAdviceInterceptor                 --》     invoke(this)
                            |         ->返回通知
                AspectJAfterAdvice
                            |         ->后置通知
                MethodBeforeAdviceInterceptor
                                      ->前置通知


总结：
    1). @EnableAspectJAutoProxy  开启aop功能
    2).@EnableAspectJAutoProxy会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
    3).AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
    4).容器创建流程：
        1.registerBeanPostProcessors()注册后置处理器，创建AnnotationAwareAspectJAutoProxyCreator
        2.finishBeanFactoryInitialization()初始化剩下的单实例bean
            1.创建业务逻辑组件和切面组件
            2.AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
            3.组件创建完后，判断组件是否需要增强
                是，切面的通知方法包装成增强器Advisor，给业务逻辑组件创建一个dialing对象cglib
    5).执行目标方法
        1).代理对象执行目标方法
        2).cglib.intercept()
            1.得到目标方法的拦截器链，增强器包装成拦截器MethodInterceptor
            2.利用拦截器的链式机制，依次进入每一个拦截器进行执行
            3.效果
                正常执行：前置通知》目标方法》后置通知》返回通知
                异常执行：前置通知》目标方法》后置通知》异常通知



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
