package edu.school21.base;

public class User {
    private String  name;
    private Integer age;
    private Double  height;
    private Boolean student;
    private Long    weight;

    public User() {
        this.name = "Default name";
        this.age = 0;
        this.height = 0D;
        this.student = false;
        this.weight = 0L;
    }

    public User(String name, Integer age, Double height, Boolean student, Long weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.student = student;
        this.weight = weight;
    }

    public int growUp(int value) {
        this.age += value;
        return age.intValue();
    }

    public double grow(double value) {
        this.height += value;
        return this.height.doubleValue();
    }

    public long weightUp(long value) {
        this.weight += value;
        return this.weight.longValue();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", student=" + student +
                ", weight=" + weight +
                '}';
    }
}
