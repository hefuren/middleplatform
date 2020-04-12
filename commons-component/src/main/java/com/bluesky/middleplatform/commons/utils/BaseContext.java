package com.bluesky.middleplatform.commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component(value = "BaseContext")
public class BaseContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static Object getBean(String name) {
        Object object = null;
        object = context.getBean(name);
        return object;
    }

    public static <T> T getBean(String name, Class<T> requiredType)
            throws BeansException {
        T object = null;
        object = (T) context.getBean(name);
        return object;
    }

    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.context = context;

    }


}
