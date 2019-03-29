package com.alpha.ioc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class BeanFactoryTest {
    private BeanFactory beanFactory;

    @Before
    public void setUp() {
        /**step1: create a bean factory*/
        beanFactory = new DefaultListableBeanFactory();
        /**step2: read the configuration metadata*/
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:spring-config.xml");
        /**step3:*/
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
        reader.loadBeanDefinitions(resource);
    }

    @Test
    public void getCar1() {
        Car car = (Car) beanFactory.getBean("car1");
        System.out.println(car);
    }

    @Test
    public void getCar2() {
        Car car = (Car) beanFactory.getBean("car2");
        System.out.println(car);
    }

    @Test
    public void getCar3() {
        Car car = beanFactory.getBean("car3",Car.class);
        System.out.println(car);
    }
}
