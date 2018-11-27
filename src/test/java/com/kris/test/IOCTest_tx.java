package com.kris.test;

import com.kris.bean.Person;
import com.kris.config.MainConfig2;
import com.kris.tx.TxConfig;
import com.kris.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTest_tx {
    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.insertUser();
        context.close();
    }
}
