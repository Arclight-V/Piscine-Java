package edu.school21.base;

public class Car {
    private String  model;
    private Integer age;
    private Double  maxSpeed;
    private Boolean sportCar;
    private Long    mileage;

    public Car() {
        this.model = "Default model";
        this.age = 0;
        this.maxSpeed = 0D;
        this.sportCar = false;
        this.mileage = 0L;
    }

    public Car(String model, Integer age, Double maxSpeed, Boolean sportCar, Long mileage) {
        this.model = model;
        this.age = age;
        this.maxSpeed = maxSpeed;
        this.sportCar = sportCar;
        this.mileage = mileage;
    }

    public int growUp(int value) {
        this.age += value;
        return age.intValue();
    }

    public double grow(double value) {
        this.maxSpeed += value;
        return this.maxSpeed.doubleValue();
    }

    public long mileageUp(long value) {
        this.mileage += value;
        return this.mileage.longValue();
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", age=" + age +
                ", maxSpeed=" + maxSpeed +
                ", sportCar=" + sportCar +
                ", mileage=" + mileage +
                '}';
    }
}

