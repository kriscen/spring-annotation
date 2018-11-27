package com.kris.tx;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "insert into t_user(user_name,user_age) values (?,?)";
        String userName = UUID.randomUUID().toString().substring(0,5);
        jdbcTemplate.update(sql,userName,12);
    }
}
