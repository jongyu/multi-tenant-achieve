package com.zhongyu.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

/**
 * Created by ZhongYu on 31/08/2017.
 */
@WebFilter(urlPatterns = "/*", filterName = "indexFilter")
class RequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class)

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init IndexFilter")
    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final uri = (request as HttpServletRequest).getRequestURI()
        if (uri.contains("dbw_")) {
            String tenant = uri.substring(uri.indexOf('dbw_'))
            CurrentTenantIdentifierResolverImpl.setCurrentTenant(tenant)
        }
        logger.info("doFilter IndexFilter")
        chain.doFilter(request, response)
    }

    @Override
    void destroy() {

    }

}
