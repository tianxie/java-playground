package me.txie.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ImageIODemo {

    public static void main(String[] args) {
        File file = new File("D:\\JMeter\\txie\\Fireworks.jpg");

        try (ImageInputStream iis = ImageIO.createImageInputStream(file);) {
            Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
            int imageIndex = 0;

            while (imageReaders.hasNext()) {
                ImageReader reader = imageReaders.next();
                System.out.printf("formatName: %s%n", reader.getFormatName());

                reader.setInput(iis);
                System.out.printf("height: %d%n", reader.getHeight(imageIndex));
                System.out.printf("width: %d%n", reader.getWidth(imageIndex));
                imageIndex++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
