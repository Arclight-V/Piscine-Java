
import java.lang.System.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PrintImage printImage = new PrintImage();

        try {
            printImage.printBMP();
        } catch (IOException e) {
            System.err.println("Exception");
        }
    }
}