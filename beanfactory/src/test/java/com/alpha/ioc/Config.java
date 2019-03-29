package com.alpha.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Car car1() {
        return new Car();
    }

    @Bean("car2")
    public Car secondCar() {
        return new Car("Ford", "black", engine1());
    }

    @Bean
    public Engine engine1() {
        Engine engine = new Engine();
        engine.setMaxPower(400);
        return engine;
    }

    @Bean
    public Engine engine2() {
        Engine engine = new Engine();
        engine.setMaxPower(300);
        return engine;
    }
}
