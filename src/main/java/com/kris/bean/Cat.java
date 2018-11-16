package com.kris.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean,DisposableBean {
    public Cat(){
        System.out.println("cat constructor..");
    }

    /**
     * 销毁方法
     * @throws Exception
     */
    public void destroy() throws Exception {
        System.out.println("cat destory..");
    }

    /**
     * 初始化方法
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat init..");
    }
}
