package GUI;

import ImageTools.ImageTransformer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaMarkerEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;

public class FileArea {
    private static GridPane gridPane = new GridPane();
    private static TextField fileNameTextField;

    public static GridPane prepare(Stage stage, ImageTransformer imageTransformer, ImageHolder imageHolder) {
        attachFileLabel();
        attachFileChoiceButton(stage, imageTransformer, imageHolder);
        return gridPane;
    }

    private static void attachFileLabel() {
        fileNameTextField = new TextField();
        fileNameTextField.setEditable(false);
        gridPane.add(fileNameTextField, 0, 0);
    }

    private static void attachFileChoiceButton(Stage stage, ImageTransformer imageTransformer, ImageHolder imageHolder) {
        Button fileChoiceButton = new Button();
        fileChoiceButton.setText("Wybierz plik");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\rchur\\OneDrive\\Pulpit\\Zajęcia\\TM\\Projekt\\"));

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
        gridPane.add(fileChoiceButton, 1, 0);
    }
}
