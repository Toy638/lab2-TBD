package com.tbd.lab1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
public class Sql2oConfiguration {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(dbUrl, dbUsername, dbPassword);
    }

    @Bean
    public Sql2o sql2o() {
        return new Sql2o(dataSource());
    }
}
