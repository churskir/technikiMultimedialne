package GUI;

import ImageTools.Scaler;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageHolder {
    final int height = 750;
    final int width = 750;
    final Canvas canvas = new Canvas(width, height);
    Scaler scaler;

    public ImageHolder() {
        scaler = new Scaler(height, width);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void showImage(BufferedImage bufferedImage) {
        Image image = SwingFXUtils.toFXImage(scaler.scale(bufferedImage), null);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.drawImage(image, 0, 0);
        }

    private BufferedImage scaleImage(BufferedImage bufferedImage) {
        int scale = Math.min(
                Math.floorDiv(this.height, bufferedImage.getHeight()),
                Math.floorDiv(this.width, bufferedImage.getWidth())
        );
        if (scale <= 1)
            return bufferedImage;
        BufferedImage scaledImage = new BufferedImage(
                scale * bufferedImage.getWidth(),
                scale * bufferedImage.getHeight(),
                java.awt.Image.SCALE_DEFAULT
        );
        for (int oldY = 0, newY = 0; oldY < bufferedImage.getHeight(); oldY++){
            for (int oldX = 0, newX = 0; oldX < bufferedImage.getWidth(); oldX++) {
                int color = bufferedImage.getRGB(oldX, oldY);
                System.out.println("[" + oldX + ", " + oldY + "] = " + color);
                for (int i = 0; i < scale; i++) {
                    scaledImage.setRGB(newX++, oldY, color);
                }
            }
        }
        return scaledImage;
    }
}
