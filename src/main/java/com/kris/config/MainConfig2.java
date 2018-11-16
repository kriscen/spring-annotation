package com.kris.config;

import com.kris.bean.Color;
import com.kris.bean.ColorFactoryBean;
import com.kris.bean.Person;
import com.kris.bean.Red;
import com.kris.condition.LinuxCondition;
import com.kris.condition.MyImportBeanDefinitionRegistrar;
import com.kris.condition.MyImportSelector;
import com.kris.condition.WindowsCondition;
import org.springframework.context.annotation.*;
//类中组件统一设置  满足当前条件，当前配置才生效
@Conditional({WindowsCondition.class})
@Configuration
@Import({Color.class,Red.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {

    //默认单实例
    @Bean("person")
    /*
         @Scope()
         默认单例
         prototype:多实例  ioc容器启动不会调用方法创建对象，
                            每次获取才会调用方法创建对象
         singleton:单实例  ioc容器启动会调用方法创建对象放到ioc容器中，
                            以后每次回去就是直接从容器总拿

         request:同一次请求创建一个实例
         session:同一个session创建一个实例
     */
    //@Scope()
    /*
        懒加载：用到才加载
     */
    //@Lazy
    public Person person(){
        System.out.println("创建对象");
        return new Person("李四",25);
    }
    /*
        @Conditional:按照一定的条件进行判断，满足条件给容器中注册bean
        windows注册bill
        linux注册linus

     */
    @Bean("bill")
    @Conditional({WindowsCondition.class})
    public Person person1(){
        return new Person("Bill Gates",62);
    }

    @Bean("linus")
    @Conditional({LinuxCondition.class})
    public Person person2(){
        return new Person("linus",48);
    }

    /**
     * 给容器中注册组件方式：
     *  1.包扫描+组件标注注解(@controller/@service/@repostory/@componen)
     *  2.@bean[导入的第三方包里面的组件]
     *  3.@import [快速给容器中导入组件]
     *      1)@import(要导入到容器中的组件)，容器会自动注册，id默认全类名
     *      2)importSelector:返回需要导入的组件的全类名数组
     *      3)ImportBeanDefinitionRegistrar:手动注册bean到容器中
     *   4.使用Spring提供的FactoryBean
 *          1)默认获取到工厂bean调用getObject创建的对象
     *      2)要获取工厂bean本身，需要在id前面加一个&标识
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
