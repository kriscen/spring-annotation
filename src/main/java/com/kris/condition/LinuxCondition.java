package com.kris.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

//判断是否linux系统
public class LinuxCondition implements Condition {
    /**
     *
     * @param context 判断条件能使用的上下文
     * @param metadata 注释信息
     * @return
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //判断是否linux系统
        //1.能获取到ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //2.得到类加载器
        ClassLoader loader = context.getClassLoader();
        //3.获取当前环境信息
        Environment environment = context.getEnvironment();
        //4.获取bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        //判断容器中bean注册情况，也可以给容器中注册bean
        boolean definition = registry.containsBeanDefinition("person");

        String property = environment.getProperty("os.name");
        if(property.contains("linux")){
            return true;
        }
        return false;
    }
}
