package com.joker.rabbitproducer.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * @version 1.0.0
 * @ClassName BrokerMessageTableConfiguration.java
 * @Package com.joker.rabbitproducer.config.database
 * @Author Joker
 * @Description $BrokerMessageTableConfiguration 进行数据库表的创建
 * @CreateTime 2021年08月31日 10:17:00
 */
@Configuration
public class BrokerMessageTableConfiguration {

    private final DataSource rabbitProducerDataSource;

    /**
     * 加载sql文件
     */
    @Value("classpath:rabbit-producer-message-schema.sql")
    private Resource resource;

    public BrokerMessageTableConfiguration(DataSource rabbitProducerDataSource) {
        this.rabbitProducerDataSource = rabbitProducerDataSource;
    }

    @Bean
    public DataSourceInitializer initDataSourceInitializer(){
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(rabbitProducerDataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    /**
     * 数据填充器 用来执行sql表结构
     * @return
     */
    private DatabasePopulator databasePopulator(){
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(resource);
        return populator;
    }


}