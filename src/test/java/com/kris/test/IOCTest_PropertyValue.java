package com.kris.test;

import com.kris.bean.Person;
import com.kris.config.MainConfigOfLifeCycle;
import com.kris.config.MainConfigOfPropertyValue;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTest_PropertyValue {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);
    @Test
    public void test01(){
        printBean(context);
        System.out.println("===================");
        Person person = (Person)context.getBean("person");
        System.out.println(person);

        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);
        context.close();
    }

    private void printBean(AnnotationConfigApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("beanName:"+name);
        }
    }
}
