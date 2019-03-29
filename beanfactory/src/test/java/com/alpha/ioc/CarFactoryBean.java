package com.alpha.ioc;

import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {
    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] infos = carInfo.split(",");
        car.setBrand(infos[0]);
        car.setColor(infos[1]);
        Engine engine = new Engine();
        engine.setMaxPower(Integer.parseInt(infos[2]));
        car.setEngine(engine);
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}
