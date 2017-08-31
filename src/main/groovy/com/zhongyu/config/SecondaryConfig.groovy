package com.zhongyu.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * Created by ZhongYu on 31/08/2017.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "transactionManagerSecondary",
        basePackages = ["com.bean.secondary"])
class SecondaryConfig {

    @Bean(name = "dataSourceSecondary")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    DataSource dataSource() {
        final dataSource = DataSourceBuilder.create().build()
        return dataSource
    }

    @Bean(name = "entityManagerFactorySecondary")
    LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder,
                                                                         @Qualifier("dataSourceSecondary") DataSource dataSource,
                                                                         JpaVendorAdapter jpaVendorAdapter) {
        final entityManagerFactoryBean = builder
                .dataSource(dataSource)
                .packages("com.bean.secondary")
                .persistenceUnit("secondaryPersistenceUnit")
                .build()
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter)
        return entityManagerFactoryBean
    }

    @Bean(name = "transactionManagerSecondary")
    PlatformTransactionManager transactionManagerSecondary(
            @Qualifier("entityManagerFactorySecondary") EntityManagerFactory
                    entityManagerFactorySecondary) {
        return new JpaTransactionManager(entityManagerFactorySecondary)
    }

}
