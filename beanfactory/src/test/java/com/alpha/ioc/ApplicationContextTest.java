package com.alpha.ioc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Map;

public class ApplicationContextTest {
    private ClassPathXmlApplicationContext classPathXmlApplicationContext;
    private AnnotationConfigApplicationContext annotationContext;
    private AnnotationConfigApplicationContext javaConfigApplicationContext;

    @Before
    public void setUp() {
        System.getProperties().setProperty("car.engine", "miniEngine");
        classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        annotationContext = new AnnotationConfigApplicationContext("com.alpha.ioc");
        javaConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
    }

    @Test
    public void xmlTest() {
        Car car = classPathXmlApplicationContext.getBean("car3", Car.class);
        System.out.println(car);
    }

    @Test
    public void annotationTest() {
        Car car = annotationContext.getBean("car3", Car.class);
        System.out.println(car);
    }

    @Test
    public void javaConfigTest() {
        Car car = javaConfigApplicationContext.getBean("car1", Car.class);
        System.out.println(car);
    }

    @Test
    public void factoryTest() {
        Car car = classPathXmlApplicationContext.getBean("car4", Car.class);
        System.out.println(car);
    }

    @Test
    public void factoryBean() {
        Car car = classPathXmlApplicationContext.getBean("car5", Car.class);
        System.out.println(car);
    }

    @Test
    public void collectionTest() {
        Car car = classPathXmlApplicationContext.getBean("car6", Car.class);
        System.out.println(car);
    }

    @Test
    public void scopeTest() {
        Car car1 = classPathXmlApplicationContext.getBean("Golf", Car.class);
        Car car2 = classPathXmlApplicationContext.getBean("Golf", Car.class);
        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car1.getEngine().hashCode());
        System.out.println(car2.getEngine().hashCode());
    }

    @Test
    public void lookupMethodTest() {
        Car car1 = classPathXmlApplicationContext.getBean("Ford", Car.class);
        Car car2 = classPathXmlApplicationContext.getBean("Ford", Car.class);
        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car1.getEngine().hashCode());
        System.out.println(car2.getEngine().hashCode());
    }

    @Test
    public void replaceMethod() {
        Car car1 = classPathXmlApplicationContext.getBean("Buick", Car.class);
        Car car2 = classPathXmlApplicationContext.getBean("Buick", Car.class);
        System.out.println(car1);
        System.out.println(car2);
    }

    @Test
    public void InheritedBeanTest() {
        Car redBuick = classPathXmlApplicationContext.getBean("redBuick", Car.class);
        Car blueBuick = classPathXmlApplicationContext.getBean("blueBuick", Car.class);
        System.out.println(redBuick);
        System.out.println(blueBuick);
    }

    @Test
    public void dependsOnTest() {
        Car toyota = classPathXmlApplicationContext.getBean("toyota", Car.class);
        System.out.println(toyota);
    }

    @Test
    public void lifecycleTest() {
        Car nissan = classPathXmlApplicationContext.getBean("nissan", Car.class);
    }

    @Test
    public void propertiesTest() {
        Car modeledCar = classPathXmlApplicationContext.getBean("ModeledCar", Car.class);
        System.out.println(modeledCar);
    }

    @Test
    public void environment() {
        ConfigurableEnvironment environment = classPathXmlApplicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        for (PropertySource<?> propertySource : propertySources) {
            System.out.println(propertySource.getName());
        }
        Map<String, Object> systemProperties = environment.getSystemProperties();
        systemProperties.forEach((k, v) -> System.out.println(k + " : " + v));
        Car miniCar = classPathXmlApplicationContext.getBean("miniCar", Car.class);
        System.out.println(miniCar);
    }

    @Test
    public void awareCapabilityTest() {
        ManagerBean manager = classPathXmlApplicationContext.getBean("manager", ManagerBean.class);
        manager.publishManageMessage();
    }

    @Test
    public void propertyEditorTest(){
        Car newCar = classPathXmlApplicationContext.getBean("newCar", Car.class);
        System.out.println(newCar.getProductionDate());
    }

    @After
    public void tearDown() throws Exception {
        classPathXmlApplicationContext.close();
        annotationContext.close();
        javaConfigApplicationContext.close();
    }
}
