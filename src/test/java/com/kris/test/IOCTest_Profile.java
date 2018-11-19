package com.kris.test;

import com.kris.bean.Car;
import com.kris.bean.Color;
import com.kris.config.MainConfigOfAutowired;
import com.kris.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.Map;

public class IOCTest_Profile {
   // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

    /**
     * 1.使用命令行动态参数:在虚拟机参数位置加载-Dspring.profiles.active=test
     * 2.
     */
    @Test
    public void test01(){
        //1.创建applicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //2.设置环境
        context.getEnvironment().setActiveProfiles("test");
        //3.注册
        context.register(MainConfigOfProfile.class);
        //4.刷新
        context.refresh();
        //2.设置需要激活的环境
        String[] names = context.getBeanNamesForType(DataSource.class);
        for (String name : names) {
            System.out.println(name);
        }

    }

    private void printBean(AnnotationConfigApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("beanName:"+name);
        }
    }
}
