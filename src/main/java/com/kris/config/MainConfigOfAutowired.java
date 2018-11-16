package com.kris.config;

import com.kris.bean.Person;
import org.springframework.context.annotation.*;

/**
 *  自动装配：
 *      spring利用依赖注入DI，完成IOC容器中各个组件的依赖关系赋值
 *   1}autowired    自动注入
        优先按照类型去容器中找对应的组件
        后面按名字去找
        BookSerive{
            @Qualifier//指定名字
            @Autowired
            BookDao bookDao;
        }
 */
@Configuration
public class MainConfigOfAutowired {

}
