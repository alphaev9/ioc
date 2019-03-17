package com.alpha.ioc.di;

import com.alpha.ioc.di.util.PackageScanner;

import java.util.HashMap;

public class IocContainer<T> {
    HashMap<Class<T>, T> pool = new HashMap<>();

    public void initialize() {
    }

    public T getComponnet(Class<T> clazz) {
        return pool.get(clazz);
    }
}
