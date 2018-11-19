package com.kris.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//默认记载ioc容器中的组件，容器启动会调用无参构造器，再初始化操作
@Component
public class Boss {
    private Car car;

    //构造器要用的组件，也是从容器中获取
    //@Autowired
    public Boss(@Autowired Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
    /*
        标注在方法上，spring容器创建当前对象，就会调用方法，完成赋值；
        方法使用的参数，自定义类型的值从ioc容器中获取
     */
    //@Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
