package edu.school21.app;

import com.google.inject.internal.util.Classes;
import edu.school21.base.Car;
import edu.school21.base.User;
import edu.school21.logic.ReflectionChecker;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ReflectionChecker checker = new ReflectionChecker();
        Car car = new Car();
        User user = new User();

        checker.showFieldsAnnotations(car);

        System.out.println();
        checker.showFieldsAnnotations(user);

        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("edu.school21.base"))));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        for (Class<?> set : classes) {

            System.out.println(set.getName());
        }


    }

}
