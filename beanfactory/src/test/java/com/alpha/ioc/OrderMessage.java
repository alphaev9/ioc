package com.alpha.ioc;

import org.springframework.context.ApplicationEvent;

public class OrderMessage extends ApplicationEvent {
    private String message;

    public OrderMessage(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
