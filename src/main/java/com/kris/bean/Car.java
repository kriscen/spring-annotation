package com.kris.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {

    public Car(){
        System.out.println("创建car实例");
    }

    public void init(){
        System.out.println("car初始化...");
    }

    public void destory(){
        System.out.println("car销毁...");
    }
}
