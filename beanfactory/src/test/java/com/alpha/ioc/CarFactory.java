package com.alpha.ioc;

public class CarFactory {
    /**/
    public Car buildCar() {
        Engine engine = new Engine();
        engine.setMaxPower(400);
        return new Car("Buick","black",engine);
    }
}
