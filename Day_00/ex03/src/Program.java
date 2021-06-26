
import java.util.Scanner;

public class Program {

    public static void printError() {
        System.out.println("Illegal Argument");
        System.exit(-1);
    }

    public static void printStatistic(long estimation, int week, int count) {
        long tmpEstimate, devisior = 1;
        for (int i = 1; i < count; ++i) {
            devisior *=10;
        }

        for (int firstWeek = week - count + 1; firstWeek <= week; ++firstWeek) {
            tmpEstimate = estimation / devisior;
            estimation -= tmpEstimate * devisior;
            devisior /=10;
            System.out.print("Week " + firstWeek + " ");
            for (long i = 0; i < tmpEstimate; i++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }

    public static long addEstimate(Scanner in, long estimation) {
        int minEstimate = 9;
        in.nextLine();
        for (int numberOfEstimate = 0, estimate = 0; numberOfEstimate < 5; ++numberOfEstimate) {
            if (in.hasNextInt()) {
                estimate = in.nextInt();
                if (estimate < 1 || estimate > 9) {
                    in.close();
                    printError();
                }
                minEstimate = estimate < minEstimate ? estimate : minEstimate;
            } else {
                in.close();
                printError();
            }
        }
        return estimation * 10 + minEstimate;
    }

    public static void main(String args[]) {
        long estimation = 0;
        int previousWeek = 0, week = 0, countWeek = 0;
        Scanner in = new Scanner(System.in);
        String str = in.next();

        if (str.equals("Week")) {
            if (in.hasNextInt()) {
                previousWeek = in.nextInt();
                countWeek = 1;
            } else {
                in.close();
                printError();
            }
            estimation = addEstimate(in, estimation);
        }
        else if (str.equals("42")) {
            printStatistic(estimation, countWeek, week);
            System.exit(0);;
        } else {
            in.close();
            printError();
        }

        for (; countWeek < 18; countWeek++, previousWeek = week) {
            str = in.next();
            if (str.equals("Week")) {
                if (in.hasNextInt()) {
                    week = in.nextInt();
                    if (week - 1 != previousWeek) {
                        in.close();
                        printError();
                    }
                } else {
                    in.close();
                    printError();
                }
                estimation = addEstimate(in, estimation);
            }
            else if (str.equals("42")) {
                break;
            } else {
                in.close();
                printError();
            }
        }
        printStatistic(estimation, week, countWeek);
        System.exit(0);
    }
}