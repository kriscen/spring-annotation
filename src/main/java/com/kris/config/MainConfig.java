package com.kris.config;

import com.kris.bean.Person;
import com.kris.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//配置类
@Configuration//告诉Spring这是一个配置类
/*@ComponentScan(value = "com.kris",excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})*/
@ComponentScan(value = "com.kris",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyFilter.class})
                },useDefaultFilters = false)

/**@ComponentScans  value:写多个@ComponentScan
 * @ComponentScan  value:指定要扫描的包
 * excludeFilters = Filter[]:指定扫描的时候按照什么规则排除哪些
 *includeFilters = Filter[]:指定只包括哪些类型，必须配置useDefaultFilters:取消默认扫描
            type:ANNOTATION 按照注解
            ASSIGNABLE_TYPE 给定类型，比如BookSeriver.class
            ASPECTJ         织入表达式
            REGEX           正则表达式
            CUSTOM          自定义规则
 *
 */
public class MainConfig {

    /*
        给容器中注册一个bean，类型为返回值类型，默认方法名作为id
        value为id
     */
    @Bean(value = "person")
    public Person person(){
        return new Person("zhangsan",15);
    }
}
