import java.io.IOException;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PrintImage {

    public PrintImage() {}

    public void printBMP() throws IOException {
        File file = new File("../../it.bmp");
        BufferedImage source = ImageIO.read(file);

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