package com.alpha.ioc;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class Replacer implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) {
       /* String[] colors = {"white", "black", "red", "blue", "gray"};
        Random random = new Random();
        int i = random.nextInt(5);*/
        return "gray";

    }
}
