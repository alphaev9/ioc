package com.alpha.ioc.di;

import com.alpha.ioc.di.annotation.Component;
import com.alpha.ioc.di.annotation.Inject;
import com.alpha.ioc.di.annotation.Profile;
import com.alpha.ioc.di.util.PackageScanner;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class IocContainer {
    private Set<Class> components = new HashSet();

    public void initialize(File webContextRoot) {
        Predicate<String> predicate = s -> {
            try {
                if (!s.startsWith("com.alpha")) {
                    return false;
                }
                ClassLoader loader = IocContainer.class.getClassLoader();
                Class<?> aClass = loader.loadClass(s);
                return aClass.isAnnotationPresent(Component.class);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return false;
        };
        Set<String> set = PackageScanner.scanPackage(webContextRoot, predicate);
        set.forEach(component -> {
            try {
                Class aClass = Class.forName(component);
                components.add(aClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public Object getComponent(Class component) {
        return getComponent(component, null);
    }

    public Object getComponent(Class component, Profile profile) {
        /**1、if profile annotation doesnt exist,inject the only one which implemented this interface*/
        /**2、check profile annotation,if exist,find the proper component and inject*/
        Predicate<Class> predicate = clazz -> {
            if (profile == null) {
                return component.isAssignableFrom(clazz);
            } else {
                if (!clazz.isAnnotationPresent(Profile.class)) {
                    return false;
                }
                String value = ((Profile) clazz.getAnnotation(Profile.class)).value();
                if (profile.value().equals(value)) {
                    return true;
                }
                return false;
            }
        };
        Class realClass = component;
        if (component.isInterface()) {
            realClass = components.stream()
                    .filter(predicate)
                    .findFirst()
                    .get();
        }
        Object instance = null;
        try {
            instance = realClass.newInstance();
            Field[] fields = realClass.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Inject.class)) {
                    continue;
                }
                Object realFields = getComponent(field.getType(), field.getAnnotation(Profile.class));
                field.setAccessible(true);
                field.set(instance, realFields);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;

    }

}
