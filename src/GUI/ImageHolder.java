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
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.drawImage(image, 0, 0);
        }
}
