package com.kris.test;

import com.kris.aop.MathCalculator;
import com.kris.config.MainConfigOfAOP;
import com.kris.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Aop {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator mathCalculator = context.getBean(MathCalculator.class);
        int div = mathCalculator.div(4, 0);
    }

    private void printBean(AnnotationConfigApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("beanName:"+name);
        }
    }
}
