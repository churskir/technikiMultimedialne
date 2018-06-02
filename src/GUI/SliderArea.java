package GUI;

import javafx.scene.control.Slider;

public class SliderArea {
    public static Slider preapreSlider() {
        Slider slider = new Slider(1, 7, 4);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.valueProperty().addListener((observableValue, oldValue, newValue) ->
            slider.setValue(Math.round(newValue.doubleValue()))
        );
        return slider;
    }
}
