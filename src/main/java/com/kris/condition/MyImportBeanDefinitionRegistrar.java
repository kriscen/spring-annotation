package com.kris.condition;

import com.kris.bean.RainBow;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *  AnnotationMetadata:当前类的注解信息
     *  BeanDefinitionRegistry:BeanDefinition注册类
     *      把所有需要添加容器中的bean:调用registry.registerBeanDefinition();
     *
     *
     * @param importingClassMetadata
     * @param registry
     *
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.kris.bean.Red");
        boolean bule = registry.containsBeanDefinition("com.kris.bean.Bule");
        if(red && bule){
            //指定bean定义信息:bean类型，作用域等等
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            //注册bean，指定bean名字
            registry.registerBeanDefinition("rainBow",rootBeanDefinition);
        }
    }
}
