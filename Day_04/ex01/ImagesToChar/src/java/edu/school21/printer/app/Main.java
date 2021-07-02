package edu.school21.printer.app;
import  edu.school21.printer.logic.PrintImage;

import java.lang.System.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            PrintImage printImage = new PrintImage();
            printImage.printBMP();
        } catch (IOException e) {
            System.err.println("Unable to open the field");
        }
    }
}