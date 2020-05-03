package com.db.tradefinance.config;

import com.db.tradefinance.common.dao.impl.GenericDaoImpl;
import com.db.tradefinance.interceptor.CorrelationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    CorrelationInterceptor correlationInterceptor;
    private static final Logger LOG = LoggerFactory.getLogger(InterceptorConfig.class);
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOG.info("Interceptors have been registered. "+correlationInterceptor.getClass());
        registry.addInterceptor(correlationInterceptor);
    }
}