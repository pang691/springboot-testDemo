package com.taikang.health.iams.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageAuto implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    private static void setContext(ApplicationContext applicationContext) {
        MessageAuto.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //MessageAuto.applicationContext = applicationContext;
        setContext(applicationContext);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static MessageSource getBean(String beanId) throws BeansException {
        return (MessageSource) applicationContext.getBean(beanId);
    }

    public static MessageSource getDefaultBean() throws BeansException {
        return (MessageSource) applicationContext.getBean("messages_prompt");
    }
}