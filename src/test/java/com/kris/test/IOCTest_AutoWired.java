package com.kris.test;

import com.kris.bean.Boss;
import com.kris.bean.Car;
import com.kris.bean.Color;
import com.kris.bean.Person;
import com.kris.config.MainConfigOfAutowired;
import com.kris.config.MainConfigOfPropertyValue;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTest_AutoWired {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
    @Test
    public void test01(){
        printBean(context);
        Color color = context.getBean(Color.class);
        System.out.println(color);//Boss{car=com.kris.bean.Car@23986957}
        Car car = context.getBean(Car.class);
        System.out.println(car);//com.kris.bean.Car@23986957
    }

    private void printBean(AnnotationConfigApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("beanName:"+name);
        }
    }
}
