package com.kris.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @profile:
 *      Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能.
 *          指定组件在哪个环境下才能被注册到容器中。不指定，任何环境下都能被注册.
 *          1.加了环境标识的bean，只有在这个环境被激活的时候才注册，默认default
 *          2.写在配置类上，只有是指定的环境的时候，整个配置类的配置才开始生效
 *          3.没有标注环境标识的bean，在任何环境下都加载
 *  开发环境、测试环境、生产环境
 *  数据源(/A)(/B)(/C)
 */
@Profile("test")
@PropertySource(value = "classpath:/dbconfig.properties")
public class MainConfigOfProfile implements EmbeddedValueResolverAware {
    //获取资源文件信息
    @Value("${db.user}")
    private String user;
    private StringValueResolver resolver;

    @Profile("test")
    @Bean(value = "testDataSource")
    //获取资源文件信息
    public DataSource dataSourceTest(@Value("${db.password}")String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/javatest");
        dataSource.setDriverClass(resolver.resolveStringValue("${db.driverClass}"));
        return dataSource;
    }
    @Profile("dev")
    @Bean(value = "devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}")String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/devtest");
        dataSource.setDriverClass(resolver.resolveStringValue("${db.driverClass}"));
        return dataSource;
    }
    @Profile("prod")
    @Bean(value = "prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}")String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prodtest");
        dataSource.setDriverClass(resolver.resolveStringValue("${db.driverClass}"));
        return dataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }
}
