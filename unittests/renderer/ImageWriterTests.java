package renderer;

import org.junit.Test;
import java.awt.*;

/**
 * Class to write a grid to image and create it
 * **/
public class ImageWriterTests {

    /**
     * Check if the image create grids by intervals of pixels
     * **/
    @Test
    public void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("image1", 1600, 1000, 800, 500);
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 800; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(j, i, Color.white);
                }
            }
        }
        imageWriter.writeToImage();
    }
}