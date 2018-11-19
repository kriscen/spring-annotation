package com.kris.bean;

public class Color {
    private Car car;

    @Override
    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
