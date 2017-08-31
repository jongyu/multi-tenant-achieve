package com.zhongyu.config

import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * Created by ZhongYu on 31/08/2017.
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = ["com.bean.primary"])
class PrimaryConfig {

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    DataSource dataSource() {
        final dataSource = DataSourceBuilder.create().build()
        return dataSource
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter,
            MultiTenantConnectionProvider multiTenantConnectionProviderImpl,
            CurrentTenantIdentifierResolver currentTenantIdentifierResolverImpl,
            JpaProperties jpaProperties
    ) {
        Map<String, Object> properties = new HashMap<>()
        properties.putAll(jpaProperties.getHibernateProperties(dataSource))
        properties.put("hibernate.multiTenancy", 'SCHEMA')
        properties.put("hibernate.multi_tenant_connection_provider", multiTenantConnectionProviderImpl)
        properties.put("hibernate.tenant_identifier_resolver", currentTenantIdentifierResolverImpl)

        final entityManagerFactoryBean = builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("com.bean.primary")
                .persistenceUnit("primaryPersistenceUnit")
                .build()
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter)
        return entityManagerFactoryBean
    }

    @Primary
    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory)
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter()
    }

}
