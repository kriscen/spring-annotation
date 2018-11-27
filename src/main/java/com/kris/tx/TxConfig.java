package com.kris.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public
}
