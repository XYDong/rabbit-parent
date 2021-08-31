package com.joker.rabbitproducer.config.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @version 1.0.0
 * @ClassName RabbitProducerDataSourceConfiguration.java
 * @Package com.joker.rabbitproducer.config.database
 * @Author Joker
 * @Description 数据源配置
 * @PropertySource 加载配置文件
 * @CreateTime 2021年08月31日 10:21:00
 */
@Slf4j
@Configuration
@PropertySource({"classpath:rabbit-producer-message.properties"})
public class RabbitProducerDataSourceConfiguration {

    public RabbitProducerDataSourceConfiguration() {
        log.info("Initializing RabbitProducerDataSourceConfiguration");
    }

    /**
     * druid连接池加载
     */
    @Value("${rabbit.producer.druid.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     * 配置数据源
     * @return
     * @throws SQLException
     */
    @Bean(name = "rabbitProducerDataSource")
    @ConfigurationProperties(prefix = "rabbit.producer.druid.jdbc")
    @Primary
    public DataSource rabbitProducerDataSource() throws SQLException {
        DataSource rabbitProducerDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        log.info("rabbitProducerDataSource: {}", rabbitProducerDataSource);
        return rabbitProducerDataSource;
    }

    public DataSourceProperties primaryDataSourceProperties(){
        return new DataSourceProperties();
    }

    public DataSource primaryDataSource(){
        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
    }
}
