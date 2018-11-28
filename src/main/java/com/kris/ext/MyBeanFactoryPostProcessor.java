package com.kris.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        int count = beanFactory.getBeanDefinitionCount();
        System.out.println(count);
        String[] names = beanFactory.getBeanDefinitionNames();
        System.out.println(Arrays.asList(names));
    }

}
