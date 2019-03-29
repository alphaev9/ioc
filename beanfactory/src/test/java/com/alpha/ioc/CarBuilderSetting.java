package com.alpha.ioc;

public class CarBuilderSetting {
    private String color;
    private Engine engine;


    public CarBuilderSetting(String color, Engine engine) {
        this.color = color;
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public Engine getEngine() {
        return engine;
    }
}
