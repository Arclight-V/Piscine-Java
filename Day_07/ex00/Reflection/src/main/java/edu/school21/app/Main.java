package edu.school21.app;

import edu.school21.base.Car;
import edu.school21.base.User;
import edu.school21.logic.ReflectionChecker;

public class Main {
    public static void main(String[] args) {
        ReflectionChecker checker = new ReflectionChecker();
        Car car = new Car();
        User user = new User();

        checker.showClassName(car);
        checker.showClassFields(car);
        checker.showClassName(user);
        checker.showClassFields(user);
    }
}
