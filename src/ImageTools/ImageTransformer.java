package ImageTools;

import GUI.ImageHolder;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageTransformer {

    private BufferedImage image = null;
    private final int white = Color.WHITE.getRGB();
    private final int black = Color.BLACK.getRGB();

    public ImageTransformer() {
    }

    public BufferedImage readImage(String path) {
        this.image = ImageReader.read(path);
        return image;
    }

    public BufferedImage applyDilation() {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                java.awt.Image.SCALE_DEFAULT
        );
        for (int x = 0; x < image.getHeight(); x++)
            for (int y = 0; y < image.getWidth(); y++) {
                if ((image.getRGB(x, y) == white) && (getNeighbourhood(x, y) < 1))
                    newImage.setRGB(x, y, white);
                else
                    newImage.setRGB(x, y, black);
            }
        this.image = newImage;
        return this.image;
    }

    public BufferedImage applyErosion() {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                java.awt.Image.SCALE_DEFAULT
        );
        for (int x = 0; x < image.getHeight(); x++)
            for (int y = 0; y < image.getWidth(); y++) {
                if ((image.getRGB(x, y) == black) && (getNeighbourhood(x, y) > 3))
                    newImage.setRGB(x, y, black);
                else
                    newImage.setRGB(x, y, white);
            }
        this.image = newImage;
        return this.image;
    }

    private int getNeighbourhood(int x, int y) {
        int neighbours = 0;
        if (!isWhite(x - 1, y - 1))
            neighbours++;
        if (!isWhite(x - 1, y))
            neighbours++;
        if (!isWhite(x - 1, y + 1))
            neighbours++;
        if (!isWhite(x, y + 1))
            neighbours++;
        if (!isWhite(x + 1, y + 1))
            neighbours++;
        if (!isWhite(x + 1, y))
            neighbours++;
        if (!isWhite(x + 1, y - 1))
            neighbours++;
        if (!isWhite(x, y - 1))
            neighbours++;
        return neighbours;
    }

    private boolean isWhite(int x, int y) {
        if (x < 0 || x >= image.getHeight() || y < 0 || y >= image.getWidth())
            return true;
        return image.getRGB(x, y) == white;
    }
}
