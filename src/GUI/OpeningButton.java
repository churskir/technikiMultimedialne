package GUI;

import ImageTools.ImageTransformer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.awt.image.BufferedImage;

public class OpeningButton {
    private static ImageTransformer transformer;
    private static ImageHolder holder;
    private static Slider theSlider;

    public static Button prepare(ImageTransformer imageTransformer, ImageHolder imageHolder, Slider slider) {
        transformer = imageTransformer;
        holder = imageHolder;
        theSlider = slider;
        return prepareDilationButton();
    }

    private static Button prepareDilationButton() {
        Button transButton = new Button();
        transButton.setText("Zastosuj otwarcie");

        transButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    try {
                        BufferedImage bufferedImage = transformer.applyOpening((int) theSlider.getValue());
                        holder.showImage(bufferedImage);
                    } catch (NullPointerException ex) {
                        System.out.println("[APP] Applying openings before image was set");
                    }
                }
            }
        );
        return transButton;
    }
}
