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

    public BufferedImage applyDilation(int level) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                java.awt.Image.SCALE_DEFAULT
        );
        for (int y = 0; y < image.getHeight(); y++)
            for (int x = 0; x < image.getWidth(); x++) {
                if ((image.getRGB(x, y) == white) && (getNeighbourhood(x, y) < level))
                    newImage.setRGB(x, y, white);
                else
                    newImage.setRGB(x, y, black);
            }
        this.image = newImage;
        return this.image;
    }

    public BufferedImage applyErosion(int level) {
        level = 8 - level;
        BufferedImage newImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                java.awt.Image.SCALE_DEFAULT
        );
        for (int y = 0; y < image.getHeight(); y++)
            for (int x = 0; x < image.getWidth(); x++) {
                if ((image.getRGB(x, y) == black) && (getNeighbourhood(x, y) > level))
                    newImage.setRGB(x, y, black);
                else
                    newImage.setRGB(x, y, white);
            }
        this.image = newImage;
        return this.image;
    }

    public BufferedImage applyOpening(int level) {
        applyErosion(level);
        return applyDilation(level);
    }

    public BufferedImage applyClosing(int level) {
        applyDilation(level);
        return applyErosion(level);
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
        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight())
            return true;
        return image.getRGB(x, y) == white;
    }
}
