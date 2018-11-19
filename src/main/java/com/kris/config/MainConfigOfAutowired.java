package com.kris.config;

import com.kris.bean.Car;
import com.kris.bean.Color;
import com.kris.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
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
    3）@Autowired:可以标注在构造器、参数、方法、属性上：都是从容器中获取参数组件
            1.标注方法位置：例如set方法和@bean
                            @Bean+方法参数，参数从容器中获取；默认不写Autowired
            2.构造器，如果组件只有一个有参构造器，这个构造器的Autowired可以省略，
                                    参数位置的组件还是可以从组件很中获取
            3.放在参数位置
    4)自定义组件想要使用Spring容器底层的一些组件(ApplicationContext,BeeanFactory...)
        自定义组件实现XXXAware:看red.class实现
        XXXAware：使用XXXProcessor处理
            例如：ApplicationcontextAware-》ApplicationContextProcessor
 

 */
@Configuration
@ComponentScan(value = "com.kris.bean")
public class MainConfigOfAutowired {
    @Bean
    public Color color(@Autowired Car car){//可以不标@autowired
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
