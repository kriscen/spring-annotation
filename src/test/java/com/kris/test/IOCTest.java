package com.kris.test;

import com.kris.bean.Person;
import com.kris.config.MainConfig;
import com.kris.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void testImport(){
        printBean(context);
        //工厂bean获取调用的是getObject对象
        Object colorFactoryBean1 = context.getBean("colorFactoryBean");
        Object colorFactoryBean2 = context.getBean("colorFactoryBean");
        System.out.println("bean类型:"+colorFactoryBean1.getClass());//class com.kris.bean.Color
        System.out.println(colorFactoryBean1.equals(colorFactoryBean2));
        Object colorFactoryBean3 = context.getBean("&colorFactoryBean");
        System.out.println("bean类型:"+colorFactoryBean3.getClass());//class com.kris.bean.ColorFactoryBean
    }

    private void printBean(AnnotationConfigApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("beanName:"+name);
        }
    }


    @Test
    public void test3(){
        ConfigurableEnvironment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);
        String[] beanNames = context.getBeanNamesForType(Person.class);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }


    @Test
    public void test2(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
        printBean(context);
        Object person = context.getBean("person");
        System.out.println(person);
    }
    @Test
    /**
     * 查看包扫描内spring容器注册了哪些bean
     */
    public void test1(){
        printBean(context);
    }
}
