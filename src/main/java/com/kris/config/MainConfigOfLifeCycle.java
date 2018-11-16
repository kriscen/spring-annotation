package com.kris.config;

import com.kris.bean.Car;
import com.kris.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * bean的生命周期
 *          bean初始化->初始化->销毁
    容器管理bean的生命周期;
    可以自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候会调用自定义的初始化方法

 单例时候
    初始化:
        在对象创建完成并且赋值好，调用初始化方法
    销毁:
        在容器关闭的时候进行销毁
 多例
     初始化:
        在对象创建完成并且赋值好，调用初始化方法
     销毁:
        容器不会管理bean，需要自己销毁

    1)指定初始化方法和销毁方法
        xml配置:init-method    destory-method
        注解配置: @Bean(initMethod = "init",destroyMethod = "destory")
    2)实现 InitializingBean,DisposableBean接口
    3)使用JSR250
        @PostConstruct:在bean创建完成并且赋值完成，执行初始化方法
        @PreDestroy：在容器销毁bena之前通知我们进行清理工作
    4)BeanPostPocessor,在初始化前后进行一些处理工作(后置处理器)
        postProcessBeforeInitialization
        postProcessAfterInitialization

    BeanPostPocessor原理
        遍历得到容器中所有的BeanPostProcessor,依次执行beforeInitialization,
        一旦返回nul，跳出for循环，不会执行后面的BeanPostProcessor.postProcessorsBeforInitialization

    populateBean(beanName,mbd,instanceWrapper);//给bean进行属性赋值
    initializeBean{
        applyBeanPostProcessorsBeforeInitialization(WrappedBean,beanName);
        invodeInitMethods(beanName,WrappedBean,mbd);//执行自定义初始化
        applyBeanPostProcessorsAfterInitialization(wrappedBean,beanName);
    }


    spring底层对BeanPostProcessor的使用

 */
@Configuration
@ComponentScan(value = "com.kris.bean")
public class MainConfigOfLifeCycle {

    @Bean(initMethod = "init",destroyMethod = "destory")
    public Car car(){
        return new Car();
    }


}
