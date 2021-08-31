package com.joker.rabbitproducer.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.springframework.core.io.support.ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX;

/**
 * @version 1.0.0
 * @ClassName RabbitProduceMyBatisConfiguration.java
 * @Package com.joker.rabbitproducer.config.database
 * @Author Joker
 * @Description
 * @CreateTime 2021年08月31日 10:20:00
 */
@Configuration
@AutoConfigureAfter(value = {RabbitProducerDataSourceConfiguration.class})
public class RabbitProduceMyBatisConfiguration {

    @Resource(name = "rabbitProducerDataSource")
    private DataSource rabbitProducerDataSource;

    @Bean(name = "rabbitProducerSqlSessionFactory")
    public SqlSessionFactory rabbitProducerSqlSessionFactory(DataSource rabbitProducerDataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(rabbitProducerDataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            // 指定 MyBatis 的 XML 配置文件路径
            bean.setMapperLocations(resolver.getResources(CLASSPATH_ALL_URL_PREFIX + "com/joker/rabbitproducer/mapping/*.xml"));
            SqlSessionFactory sqlSessionFactory = bean.getObject();
            sqlSessionFactory.getConfiguration().setCacheEnabled(Boolean.TRUE);
            return sqlSessionFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(name="rabbitProducerSqlSessionTemplate")
    public SqlSessionTemplate rabbitProducerSqlSessionTemplate(SqlSessionFactory rabbitProducerSqlSessionFactory) {
        return new SqlSessionTemplate(rabbitProducerSqlSessionFactory);
    }
}
