package com.example.shardingdemo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @Auther: huhy
 * @Date: 2020/7/15 17:51
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPrimary",
        transactionManagerRef="transactionManagerPrimary",
        basePackages= { "com.example.shardingdemo.repository.first" }) //设置Repository所在位置
public class PrimaryDataSourceConfig {

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanAuth(
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(primaryDataSource)
                .packages("com.example.shardingdemo.entity.first") //设置实体类所在位置
                .properties(getVendorProperties(primaryDataSource))
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }
    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return localContainerEntityManagerFactoryBeanAuth(builder).getObject().createEntityManager();
    }

    protected Map<String, String> getVendorProperties(DataSource dataSource){
        return jpaProperties.getProperties();
    }

    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager platformTransactionManagerAuth(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBeanAuth(builder).getObject());
    }





}
