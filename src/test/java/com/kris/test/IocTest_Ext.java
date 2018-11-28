package com.kris.test;

import com.kris.ext.ExtConfig;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocTest_Ext {
    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig.class);

        context.publishEvent(new ApplicationEvent("发布时间") {});

        context.close();
    }
}
