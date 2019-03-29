package com.alpha.ioc;

//@Component
public class Engine {
//    @Value("400")
    private Integer maxPower;

    @Override
    public String toString() {
        return "; engine: " + maxPower+"; ";
    }

    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

}
