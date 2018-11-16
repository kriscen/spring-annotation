package com.kris.config;

import com.kris.bean.Car;
import com.kris.bean.Person;
import org.springframework.context.annotation.*;

/**
 *
 */
@Configuration
@ComponentScan(value = "com.kris.bean")
//导入配置文件
//@PropertySource({"classpath:/person.properties"})
@PropertySources({
        @PropertySource({"classpath:/person.properties"})
})
public class MainConfigOfPropertyValue {

    @Bean
    public Person  person(){
        return new Person();
    }
}
