package edu.school21.printer.logic;

import java.io.IOException;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PrintImage {

    File file;
    BufferedImage source;

    public PrintImage(String filePath) throws IOException {
        file = new File(filePath);
        source = ImageIO.read(file);
    }

    public PrintImage() throws IOException {
        file = new File("./target/resources/it.bmp");
        source = ImageIO.read(file);
    }

    public void printBMP() throws IOException {
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                Color color = new Color(source.getRGB(x, y));
                int clr = source.getRGB(y, x);
                if (clr == color.WHITE.getRGB()) {
                    System.out.print('.');
                } else if (clr == color.BLACK.getRGB()) {
                    System.out.print('0');
                }
            }
            System.out.println();
        }
    }
}