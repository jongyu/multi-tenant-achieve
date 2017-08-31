package com.zhongyu.config

import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * Created by ZhongYu on 31/08/2017.
 */
@Component
class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    static final DEFAULT_TENANT_ID = '_'

    private static Logger logger = LoggerFactory.getLogger(CurrentTenantIdentifierResolverImpl.class)
    private static ThreadLocal<String> currentTenant = new ThreadLocal<>()

    static void setCurrentTenant(String tenant) {
        logger.debug("Setting tenant to " + tenant);
        currentTenant.set(tenant);
    }

    static String getCurrentTenant() {
        return currentTenant.get()
    }

    static void clear() {
        currentTenant.set(null)
    }

    @Override
    String resolveCurrentTenantIdentifier() {
        final tenant = getCurrentTenant()
        return tenant ?: DEFAULT_TENANT_ID
    }

    @Override
    boolean validateExistingCurrentSessions() {
        return true
    }

}
