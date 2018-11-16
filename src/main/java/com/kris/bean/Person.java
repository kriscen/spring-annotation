package com.kris.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {
    /**
     * 使用@value
     *  1.基本数值
     *  2.可以写SqEL:#{}
     *  3.可以写${},取出配置文件中的值，在环境变量里面的值
     */
    @Value("张三")
    private String name;
    @Value("#{20-5}")
    private Integer age;
    @Value("${person.nickName}")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
