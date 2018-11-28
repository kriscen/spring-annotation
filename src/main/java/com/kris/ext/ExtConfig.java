package com.kris.ext;

import com.kris.bean.Bule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 扩展原理
 *      BeanPostProcessor:bean后置处理器，bean创建对象初始化前后进行拦截工作
 *      BeanFactoryPostProcessor
 *          在beanFacory标准初始化之后调用，所有bean的定义已经保存加载到beanFactory中，但是bean的实例还未加载
 *
 *1)ioc容器创建对象
 *2）invokeBeanFactoryPostProcessors（beanFactory）,执行beanFactoryPostProcessor
 *      如何找到BeanFactoryPostProcessor并执行他们的方法
 *          1)直接在beanfactory中找到所有类型是beanFactoryPostProcessor的组件，并执行他们的方法
 *          2)在初始化创建其他组件前面执行
 *
 * 3.ApplicationListener:监听容器中发布的时间.时间驱动模型开发
 *  public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 *  监听ApplicationEvent及其下面的子事件：
 *
 *  步骤：
 *      1}写一个监听器监听某个事件，applicationEvent及其子类
 *      2)把监听器加入到容器
 *      3)容器中有相关事件发布，就能监听到这个事件
*          contextRefreshedEvent:容器刷新完成(所有bean全部完成创建)，会发布这个事件
 *         contextClosedEvent：关闭容器后会发布这个事件
 *      4)发布一个事件
 *           context.publishEvent
    原理：
        ContextRefreshedEvent、IocTest_Ext$1、ContextClosedEvent

 */
@ComponentScan("com.kris.ext")
@Configuration
public class ExtConfig {
    @Bean
    public Bule bule(){
        return new Bule();
    }
}
