package com.zhongyu.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * Created by ZhongYu on 31/08/2017.
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = ["com.bean.primary"])
class PrimaryConfig {
}
