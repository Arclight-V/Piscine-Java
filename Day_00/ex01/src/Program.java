import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        if (!in.hasNextInt()) {
            in.close();
            System.exit(-1);
        }
        int number = in.nextInt(), numberOfSteps = 1;
        if (number <= 1) {
            System.out.println("Illegal Argument");
            System.exit(-1);
        }
        else if (number == 2) {
            System.out.println("true " + numberOfSteps);
        } else {
            for (int i = 2; i * i <= number; ++i) {
                if (number % i == 0) {
                    System.out.println("false " + numberOfSteps);
                    return;
                }
                ++numberOfSteps;
            }
            System.out.println("true " + numberOfSteps);
        }
        System.exit(0);
    }
}