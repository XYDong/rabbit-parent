package com.joker.rabbitproducer.config.database;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @version 1.0.0
 * @ClassName RabbitProducerMybatisMapperScannerConfig.java
 * @Package com.joker.rabbitproducer.config.database
 * @Author Joker
 * @Description
 * @CreateTime 2021年08月31日 10:22:00
 */
@Configuration
@AutoConfigureAfter(RabbitProducerDataSourceConfiguration.class)
public class RabbitProducerMybatisMapperScannerConfig {
    @Bean(name="rabbitProducerMapperScannerConfigurer")
    public MapperScannerConfigurer rabbitProducerMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("rabbitProducerSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.joker.rabbitproducer.mapper");
        return mapperScannerConfigurer;
    }
}
