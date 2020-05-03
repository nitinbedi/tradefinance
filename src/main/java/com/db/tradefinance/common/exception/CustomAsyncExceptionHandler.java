package com.db.tradefinance.common.exception;

import java.lang.reflect.Method;

import com.db.tradefinance.controller.mvc.TradeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);
    @Override
    public void handleUncaughtException(final Throwable throwable, final Method method, final Object... obj) {
        LOG.info("Exception message - " + throwable.getMessage());
        LOG.info("Method name - " + method.getName());
        for (final Object param : obj) {
            LOG.info("Param - " + param);
        }
    }

}