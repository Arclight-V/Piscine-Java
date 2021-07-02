
import java.lang.System.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args[0] == null) {
            System.err.println("there are no input parameters");
            System.exit(1);
        }
        try {
            PrintImage printImage = new PrintImage(args[0]);
            printImage.printBMP();
        } catch (IOException e) {
            System.err.println("Unable to open the fiel: " + args[0]);
        }
    }
}