package com.kris.config;

import com.kris.bean.Person;
import org.springframework.context.annotation.*;

/**
 *  自动装配：
 *      spring利用依赖注入DI，完成IOC容器中各个组件的依赖关系赋值
 *   1}autowired    自动注入
        优先按照类型去容器中找对应的组件
        后面按名字去找
        @Qualifier//指定名字
        自动装配默认一定要将属性赋值好，没有就会报错.可以@Autowired(required=false),指定不是必须的属性
        @Primary让Spring自动装配的时候首选装配，可以使用qualifier指定特定bean
        BookSerive{
            @Qualifier//指定名字
            @Autowired(required=false)
            BookDao bookDao;
        }
    2)@Resource(JSR250)和@Inject(JSR330)注解
        @Resource：
                可以自动装配，默认按照组件名字装配
        @Inject：
                需要导包，功能和autowired一样，属性比spring的少

 */
@Configuration
public class MainConfigOfAutowired {

}
