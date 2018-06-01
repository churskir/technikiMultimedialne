package ImageTools;

import java.awt.image.BufferedImage;

public class Scaler {

    private BufferedImage sourceImage;
    private int scale;
    private int targetHeight;
    private int targetWidth;
    private int sourceX;
    private int sourceY;
    private int sourceWidth;
    private int sourceHeight;
    private BufferedImage scaledImage;

    public Scaler(int targetHeight, int targetWidth) {
        this.targetHeight = targetHeight;
        this.targetWidth = targetWidth;
    }

    public BufferedImage scale(BufferedImage sourceImage) {
        this.sourceImage = sourceImage;
        sourceX = 0;
        sourceY = 0;
        sourceWidth = sourceImage.getHeight();
        sourceHeight = sourceImage.getWidth();
        scale = Math.min(
                Math.floorDiv(targetHeight, sourceHeight),
                Math.floorDiv(targetWidth, sourceWidth)
        );
        if (scale <= 1)
            return sourceImage;
        scaledImage = new BufferedImage(
                scale * sourceImage.getWidth(),
                scale * sourceImage.getHeight(),
                java.awt.Image.SCALE_DEFAULT
        );
        do
            scalePixel();
        while(nextPixel());
        return scaledImage;
    }

    private boolean nextPixel() {
        if (sourceX < sourceHeight - 1) {
            sourceX++;
            return true;
        }
        if (sourceY < sourceWidth - 1) {
            sourceX = 0;
            sourceY++;
            return true;
        }
        return false;
    }

    private void scalePixel() {
        assert sourceX < sourceHeight;
        assert sourceY < sourceWidth;
        int color = sourceImage.getRGB(sourceX, sourceY);
        for (int targetY = scale * sourceY; targetY < scale * (sourceY + 1); targetY++)
            for (int targetX = scale * sourceX; targetX < scale * (sourceX + 1); targetX++)
                scaledImage.setRGB(targetX, targetY, color);
    }
}
