import java.util.Scanner;

public class Program {

    public static int sumOfDigit(int number) {
        int digit, sum = 0;

        while (number != 0) {
            digit = number % 10;
            number /= 10;
            sum += digit;
        }
        return sum;
    }

    public static boolean chekIsPrime(int number) {
        if (number == 2) {
            return true;
        } else {
            for (int i = 2; i * i <= number; ++i) {
                if (number % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String args[]) {
        int coutnQueries = 0, sum = 0;
        Scanner in = new Scanner(System.in);
        while (true) {
            if (in.hasNextInt()) {
                int number = in.nextInt();
                if (number > 1) {
                    if (number == 42) {
                        break;
                    }
                    sum = sumOfDigit(number);
                    if (chekIsPrime(sum)) {
                        coutnQueries++;
                    }
                }
            } else {
                in.nextLine();
            }
        }
        System.out.println("Count of coffee-request - " + coutnQueries);
    }
}