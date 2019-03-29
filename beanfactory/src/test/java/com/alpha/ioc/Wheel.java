package com.alpha.ioc;

public class Wheel {
    private String position;

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "position='" + position + '\'' +
                '}';
    }
}
