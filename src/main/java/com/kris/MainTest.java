package com.kris;

import com.kris.bean.Person;
import com.kris.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        //通过id获取
//        Object person = context.getBean("person");
        //通过类型获取
//        Person person = context.getBean(Person.class);
//        System.out.println(person);
        String[] names = context.getBeanNamesForType(Person.class);
        for (String name : names) {
            //查看所有类型的bean名字
            System.out.println(name);
        }
    }

    private static void annotationTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Object person = context.getBean("person");
        System.out.println(person);
    }
}
