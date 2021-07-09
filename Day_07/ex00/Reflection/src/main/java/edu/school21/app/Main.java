package edu.school21.app;

import edu.school21.logic.ReflectionChecker;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import static org.reflections.ReflectionUtils.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;

public class Main {
    private final String line = "---------------------";

    Set<Class<?>> classes;
    Set<Field> fields;
    Set<Method> resources;
    Set<Constructor> injectables;
    Class cl;
    Object result;
    Scanner sc;
    ReflectionChecker reflectionChecker;
    Reflections reflections;


    public Main() {
        sc = new Scanner(System.in);
        reflectionChecker = new ReflectionChecker();
    }

    private void printLine(String str) {
        System.out.println(str);
    }
    private void printstr(String str) {
        System.out.print(str);
    }

    private void printFields() {
        printLine("fields:");
        for (Field field : fields) {
            printLine( "        " + field.getType().getName().substring(10, field.getType().getName().length()) + " " + field.getName());
        }
    }

    private void printClassParams(Class<?>[] params) {
        printstr("(");
        if (params.length != 0) {
            if (params.length == 1) {
                printstr(params[0].getName());
            } else {
                int i = 0;
                for (; i < params.length - 1; ++i) {
                    printstr(params[i].getName() + ", ");
                }
                printstr(params[i].getName());
            }
        }
        printLine(")");
    }

    private void printResourses() {
        printLine("methods:");
        for (Method method : resources) {
            printstr("        " + method.getReturnType().getSimpleName() + " " + method.getName());
            Class <?>[] params = method.getParameterTypes();
            printstr("(");
            for (Class<?> param : params) {
                printstr(param.getName());
            }
            printLine(")");
        }
    }


    public void displayingListOfClasses() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false ), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("edu.school21.base"))));
        classes = reflections.getSubTypesOf(Object.class);

        printLine("Classes:");
        for (Class<?> set : classes) {
            printLine(set.getName());
        }
        printLine(line);
    }

    public void createObject() {
        printLine("Let's create an object.");
        try {
            result = cl.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setParemetrs() {
        for(Field field : fields) {
            printLine(field.getName() + ":");
            Object tmp = null;
            try {
                tmp = Class.forName(field.getType().getName());
                if (tmp == Boolean.class) {
                    while (sc.hasNextBoolean() == false) { sc.next(); }
                    tmp = sc.nextBoolean();
                } else if (tmp == String.class) {
                    while (sc.hasNext() == false) { sc.next(); }
                    tmp = sc.next();;
                } else if (tmp == Integer.class) {
                    while (sc.hasNextInt() == false) { sc.next(); }
                    tmp = sc.nextInt();
                } else if (tmp == Double.class) {
                    while (sc.hasNextDouble() == false) { sc.next(); }
                    tmp = sc.nextDouble();
                } else if (tmp == Long.class) {
                    while (sc.hasNextLong() == false) { sc.next(); }
                    tmp = sc.nextLong();
                }
                Field f = result.getClass().getDeclaredField(field.getName());
                f.setAccessible(true);
                f.set(result, tmp);
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        printLine(line);
    }

    public void callMethod() {
        printLine("Enter name of the method for call:");
        for (Method method : resources) {
            String strMethod = sc.next();
            if (method.getName().equals(strMethod)) {
                try {
                    Method callMethod = result.getClass().getMethod(strMethod);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }


            for (Method method : resources) {
                printstr("        " + method.getReturnType().getSimpleName() + " " + method.getName());
                Class <?>[] params = method.getParameterTypes();
                printstr("(");
                for (Class<?> param : params) {
                    printstr(param.getName());
                }
                printLine(")");
            }
    }

    public void printResult() {
         printLine(result.toString());
    }

    public void enterClassName() {
        printstr("Enter class name: \n");

        String next = sc.nextLine();
        printLine(line);
        for (Class<?> set : classes) {
            String name = set.getName().substring(18, set.getName().length());
            if (name.equals(next)) {
                cl = null;
                try {
                    cl = Class.forName(set.getName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                fields = getAllFields(cl);
                printFields();
                resources = getMethods(cl);
                printResourses();
            }
        }
        printLine(line);
        if(cl == null) {
            enterClassName();
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.displayingListOfClasses();
        main.enterClassName();
        main.createObject();
        main.setParemetrs();
        main.printResult();
        main.callMethod();
    }



}
