package com.alpha.ioc;

import org.springframework.context.ApplicationListener;

public class WorkerBean implements ApplicationListener<OrderMessage> {

    @Override
    public void onApplicationEvent(OrderMessage orderMessage) {
        System.out.println("roger: " + orderMessage.getMessage());
    }
}
