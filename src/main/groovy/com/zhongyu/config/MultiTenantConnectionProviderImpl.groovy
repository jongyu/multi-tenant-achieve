package com.zhongyu.config

import org.hibernate.HibernateException
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.sql.DataSource
import java.sql.Connection
import java.sql.SQLException

/**
 * Created by ZhongYu on 31/08/2017.
 */
@Component
class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

    @Autowired
    private DataSource dataSource

    @Override
    Connection getAnyConnection() throws SQLException {
        return dataSource.connection
    }

    @Override
    void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close()
    }

    @Override
    Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getConnection()
        if (tenantIdentifier) {
            try {
                connection.createStatement().execute("USE " + tenantIdentifier)
            } catch (SQLException e) {
                throw new HibernateException("Problem setting schema to " + tenantIdentifier, e)
            }
        } else {
            println "getConnection, bad tenant:$tenantIdentifier"
        }
        return connection
    }

    @Override
    void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        connection.close()
    }

    @Override
    boolean supportsAggressiveRelease() {
        return true
    }

    @Override
    boolean isUnwrappableAs(Class unwrapType) {
        return false
    }

    @Override
    <T> T unwrap(Class<T> unwrapType) {
        return null
    }

}
