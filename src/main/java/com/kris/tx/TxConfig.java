package com.kris.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 声明式事务：
 * 环境：
    1.相关依赖
 *      数据源，驱动，jdbc模块
 *  2.配置数据源，jdbcTemplate
 *  3.给方法上标注@Transactional,并开启事务
 *  4.@EnableTransactionManagement,开启基于注解的事务管理功能
 *  5.配置事务管理器配置事务
 *
 *  原理：
 *      1)@EnableTransactionManagement
 *          利用TransactionManagementConfigurationSelector给容器导入组件
 *              AutoProxyRegistrar
 *              ProxyTransactionManagementConfiguration
        2).AutoProxyRegistrar
                给容器中注册一个InfrastructureAdvisorAutoProxyCreator
                利用后置处理器机制，在对象创建后包装对象，返回一个代理对象(增强器)，代理对象执行方法利用拦截器链进行调用
*       3).ProxyTransactionManagementConfiguration
 *              1.事务增强器
 *                  事务增强器要用事务注解的信息：AnnotationTransactionAttributeSource解析事务注解
 *              2.事务拦截器
                    TransactionInterceptor：保存事务属性信息，事务
 *
 *
 */
@EnableTransactionManagement
@Configuration
@ComponentScan(value = "com.kris.tx")
public class TxConfig {

    //数据源
    @Bean
    public DataSource dataSource() throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/javatest");
        dataSource.setUser("root");
        dataSource.setPassword("admin");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return dataSource;
    }

    /**
     * spring对configuration文件会特殊处理
     *       给容器中加组件的方法，多次调用只是在容器中找组件
     * @return
     * @throws Exception
     */
    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager((dataSource()));
    }
}
