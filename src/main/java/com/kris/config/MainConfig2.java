package com.kris.config;

import com.kris.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
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
    @Lazy
    public Person person(){
        System.out.println("创建对象");
        return new Person("李四",25);
    }
}
