package com.kris.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    /**
     * 容器中发布事件以后方法触发
     * @param event
     */
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("收到事件"+event);
    }

}
