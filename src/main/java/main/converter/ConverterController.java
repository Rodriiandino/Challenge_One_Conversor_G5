package main.converter;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConverterController {

    public TextField inputTwo;
    public TextField inputOne;
    public Label valueTwo;
    public Label valueOne;
    public ChoiceBox choiceTwo;
    public ChoiceBox choiceOne;
    public Label titleCategory;


    public Button btnCurrency;
    public Button btnArea;
    public Button btnTime;
    public Button btnTemperature;
    public Button btnLength;

    public void selectCategory(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String text = sourceButton.getText();
        sourceButton.getStyleClass().add("button-active");
        switch (text) {
            case "Currency" -> {
                btnArea.getStyleClass().remove("button-active");
                btnTime.getStyleClass().remove("button-active");
                btnTemperature.getStyleClass().remove("button-active");
                btnLength.getStyleClass().remove("button-active");
            }
            case "Area" -> {
                btnCurrency.getStyleClass().remove("button-active");
                btnTime.getStyleClass().remove("button-active");
                btnTemperature.getStyleClass().remove("button-active");
                btnLength.getStyleClass().remove("button-active");
            }
            case "Time" -> {
                btnCurrency.getStyleClass().remove("button-active");
                btnArea.getStyleClass().remove("button-active");
                btnTemperature.getStyleClass().remove("button-active");
                btnLength.getStyleClass().remove("button-active");
            }
            case "Temperature" -> {
                btnCurrency.getStyleClass().remove("button-active");
                btnArea.getStyleClass().remove("button-active");
                btnTime.getStyleClass().remove("button-active");
                btnLength.getStyleClass().remove("button-active");
            }
            case "Length" -> {
                btnCurrency.getStyleClass().remove("button-active");
                btnArea.getStyleClass().remove("button-active");
                btnTime.getStyleClass().remove("button-active");
                btnTemperature.getStyleClass().remove("button-active");
            }
        }

        titleCategory.setText(text);
    }
}


