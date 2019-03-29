package com.alpha.ioc;

import java.util.Date;
import java.util.List;

//@Component("car3")
public class Car {
    //    @Value("Buick")
    private String brand;
    //    @Value("black")
    private String color;
    //    @Autowired
    private Engine engine;

    private List<Wheel> whells;

    private Date productionDate;


    public Car() {
    }

    public Car(String brand, String color, Engine engine) {
        this.brand = brand;
        this.color = color;
        this.engine = engine;
    }

    public Car(String brand, String color, Engine engine, List<Wheel> whells) {
        this.brand = brand;
        this.color = color;
        this.engine = engine;
        this.whells = whells;
    }

    @Override
    public String toString() {
        return this.hashCode() + " brand: " + brand + " ;color: " + color + engine + whells;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getColor() {
        return color;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void validate() {
        System.out.println("validate car....");
    }

    public void dispose() {
        System.out.println("car disposed!");
    }
}
