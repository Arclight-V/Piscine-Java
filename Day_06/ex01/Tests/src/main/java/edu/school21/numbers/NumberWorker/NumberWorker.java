package edu.school21.numbers.NumberWorker;

public class NumberWorker {
    public static boolean isPrime(int number) throws IllegalNumberException {
        if (number <= 1) {
            throw new IllegalNumberException();
        }
        else if (number == 2) {
            return true;
        }
        else {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int digitsSum(int number) {
        int retNum = 0, digit = 0;

        while (number != 0) {
            digit = number % 10;
            number /= 10;
            retNum = retNum + digit;
        }
        return  retNum;
    }
}
