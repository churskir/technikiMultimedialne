package GUI;

import ImageTools.ImageTransformer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;

public class Controller {
    private GridPane gridPane;
    private Button fileChoiceButton;
    private TextField fileNameTextField;
    private ImageHolder imageHolder;
    private ImageTransformer imageTransformer;

    public Controller(Stage stage, ImageHolder imageHolder, ImageTransformer imageTransformer) {
        this.imageHolder = imageHolder;
        this.imageTransformer = imageTransformer;
        gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        prepareFileLabel();
        gridPane.add(fileNameTextField, 0, 0);
        prepareFileChoiceButton(stage);
        gridPane.add(fileChoiceButton, 1, 0);
        gridPane.add(prepareDilationButton(stage), 0, 1);
        gridPane.add(prepareErosionButton(stage), 1, 1);
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    private void prepareFileLabel() {
        fileNameTextField = new TextField();
        fileNameTextField.setEditable(false);
    }

    private void prepareFileChoiceButton(Stage stage) {
        fileChoiceButton = new Button();
        fileChoiceButton.setText("Wybierz plik");
        FileChooser fileChooser = new FileChooser();

        fileChoiceButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            System.out.println("[APP] Reading file '" + file.getName() + "'");
                            System.out.println(file);
                            fileNameTextField.setText(file.getName());
                            BufferedImage bufferedImage = imageTransformer.readImage(file.getAbsolutePath());
                            imageHolder.showImage(bufferedImage);
                        } else {
                            System.out.println("[APP] Problem while reading file (file == null)");
                        }
                    }
                }
        );
    }

    private Button prepareDilationButton(Stage stage) {
        Button transButton = new Button();
        transButton.setText("Dylatacja");

        transButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        try {
                            BufferedImage bufferedImage = imageTransformer.applyDilation();
                            imageHolder.showImage(bufferedImage);
                        } catch (NullPointerException ex) {
                            System.out.println("[APP] Exception: dilatation before image was set");
                        }
                    }
                }
        );
        return transButton;
    }

    private Button prepareErosionButton(Stage stage) {
        Button transButton = new Button();
        transButton.setText("Erozja");

        transButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        try {
                            BufferedImage bufferedImage = imageTransformer.applyErosion();
                            imageHolder.showImage(bufferedImage);
                        } catch (NullPointerException ex) {
                            System.out.println("[APP] Exception: erosion before image was set");
                        }
                    }
                }
        );
        return transButton;
    }
}