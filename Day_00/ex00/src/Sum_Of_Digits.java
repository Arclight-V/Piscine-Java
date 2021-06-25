public class Sum_Of_Digits{
    public static void main(String args[]) {
        int n = 479598, digit = 0, toPrint = 0;

        digit = n % 10;
        n /= 10;
        toPrint = toPrint + digit;

        digit = n % 10;
        n /= 10;
        toPrint = toPrint + digit;

        digit = n % 10;
        n /= 10;
        toPrint = toPrint + digit;

        digit = n % 10;
        n /= 10;
        toPrint = toPrint + digit;

        digit = n % 10;
        n /= 10;
        toPrint = toPrint + digit;

        digit = n % 10;
        n /= 10;
        toPrint = toPrint + digit;

        System.out.println(toPrint);
    }
}