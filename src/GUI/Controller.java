package GUI;

import ImageTools.ImageTransformer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;

public class Controller {
    private GridPane gridPane;
    private ImageHolder imageHolder;
    private ImageTransformer imageTransformer;
    private Slider slider;

    public Controller(Stage stage, ImageHolder imageHolder, ImageTransformer imageTransformer) {
        this.imageHolder = imageHolder;
        this.imageTransformer = imageTransformer;
        gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(FileArea.prepare(stage, imageTransformer, imageHolder), 0, 0);
        slider = SliderArea.preapreSlider();
        gridPane.add(slider, 0, 1);
        gridPane.add(DilatationArea.prepare(imageTransformer, imageHolder, slider), 0, 2);
        gridPane.add(ErosionArea.prepare(imageTransformer, imageHolder, slider), 0, 3);
        gridPane.add(OpeningButton.prepare(imageTransformer, imageHolder, slider), 0, 4);
        gridPane.add(ClosingButton.prepare(imageTransformer, imageHolder, slider), 0, 5);
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}