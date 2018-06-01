import GUI.Controller;
import GUI.ImageHolder;
import ImageTools.ImageTransformer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        GridPane gridPane = getMainGridPane();

        ImageHolder imageHolder = new ImageHolder();
        gridPane.add(imageHolder.getCanvas(), 0,0);

        Controller controller = new Controller(stage, imageHolder, new ImageTransformer());
        gridPane.add(controller.getGridPane(), 1, 0);

        Scene scene = new Scene(gridPane);

        stage.setTitle("Projekt - Techniki Multimedialne");
        stage.setWidth(1050);
        stage.setHeight(750);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static GridPane getMainGridPane() {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.setAlignment(Pos.TOP_RIGHT);
        return gridPane;
    }
}