package com.alpha.ioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;

public class ManagerBean implements ApplicationContextAware {
    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public void publishManageMessage() {
        OrderMessage order = new OrderMessage(this, "keep moving...go,go,go");
        context.publishEvent(order);
    }

}
