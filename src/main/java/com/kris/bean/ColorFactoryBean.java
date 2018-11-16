package com.kris.bean;

import org.springframework.beans.factory.FactoryBean;

//创建一个spring定义的FactoryBena
public class ColorFactoryBean implements FactoryBean<Color> {
    //返回一个color对象，这个对象会添加到容器中
    public Color getObject() throws Exception {
        return new Color();
    }
    //
    public Class<?> getObjectType() {
        return Color.class;
    }

    /*
        是单例,true，bean在容器中保存一份
        多例,false，每次获取多会创建一个实例
     */
    public boolean isSingleton() {
        return false;
    }
}
