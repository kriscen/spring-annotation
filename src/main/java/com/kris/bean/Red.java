package com.kris.bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;
@Component
public class Red implements ApplicationContextAware,BeanNameAware,EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;
    private String beanName;
    private StringValueResolver stringValueResolver;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("传入的applicationContext="+applicationContext);
    }

    public void setBeanName(String name) {
        this.beanName = name;
    }

    //字符串值解析器
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
        System.out.println(this.stringValueResolver.resolveStringValue("你好，${os.name}"));
    }
}
