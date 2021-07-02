package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;

import java.io.IOException;

public class Args {

    @Parameter(names = {"--white="}, required = true)
    private String arg1;
    @Parameter(names = {"--black="}, required = true)
    private String arg2;

    public void run() throws IOException {
        try {
            PrintImage printImage = new PrintImage(arg1, arg2);
            printImage.printBMP();
        } catch (IOException e) {
            System.err.println("Unable to open the field");
        }
    }
}
