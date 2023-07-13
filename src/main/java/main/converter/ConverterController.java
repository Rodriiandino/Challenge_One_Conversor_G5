package main.converter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import main.converter.converters.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ConverterController implements Initializable {

    @FXML
    private TextField inputOne;
    @FXML
    private Label inputTwo;
    @FXML
    private Label valueTwo;
    @FXML
    private Label valueOne;
    @FXML
    private ChoiceBox<String> choiceTwo;
    @FXML
    private ChoiceBox<String> choiceOne;
    @FXML
    private Label titleCategory;

    @FXML
    private Button btnCurrency;
    @FXML
    private Button btnArea;
    @FXML
    private Button btnTime;
    @FXML
    private Button btnTemperature;
    @FXML
    private Button btnLength;

    Converter converterTime = new ConverterTime();
    ConverterTemperature converterTemperature = new ConverterTemperature();
    ConverterLength converterLength = new ConverterLength();
    ConverterArea converterArea = new ConverterArea();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConverterHelper.updateChoiceBoxes("Currency", choiceOne, choiceTwo, valueOne, valueTwo);
       setupListeners();
    }

    private void setupListeners() {
        inputOne.addEventFilter(KeyEvent.KEY_TYPED, ConverterHelper::filterInput);
        choiceOne.setOnAction(event -> ConverterHelper.onChange(choiceOne, valueOne));
        choiceTwo.setOnAction(event -> ConverterHelper.onChange(choiceTwo, valueTwo));
        inputOne.textProperty().addListener((observableValue, oldValue, newValue) -> onChangeInput());
        choiceOne.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> onChangeInput());
        choiceTwo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> onChangeInput());
    }

    public void selectCategory(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String text = sourceButton.getText();

        ConverterHelper.clearButtonStyles(btnCurrency, btnArea, btnTime, btnTemperature, btnLength);

        sourceButton.getStyleClass().add("button-active");
        titleCategory.setText(text);

        ConverterHelper.updateChoiceBoxes(text, choiceOne, choiceTwo, valueOne, valueTwo);
    }

    public void newValue(ActionEvent event) {
        String text = inputOne.getText();
        Button sourceButton = (Button) event.getSource();
        String number = sourceButton.getText();

        inputOne.setText(text + number);
    }

    public void onChangeInput() {
        String text = inputOne.getText();
        String from = choiceOne.getValue();
        String to = choiceTwo.getValue();

        if (!text.isEmpty()) {
            double value = Double.parseDouble(text);
            switch (titleCategory.getText()) {
                case "Time" -> inputTwo.setText(String.valueOf(converterTime.convert(value, from, to)));
                case "Temperature" -> inputTwo.setText(String.valueOf(converterTemperature.convert(value, from, to)));
                case "Length" -> inputTwo.setText(String.valueOf(converterLength.convert(value, from, to)));
                case "Area" -> inputTwo.setText(String.valueOf(converterArea.convert(value, from, to)));

            }
        } else {
            inputTwo.setText("");
        }
    }

    public void eraseLast() {
        String text = inputOne.getText();
        if (!text.isEmpty()) {
            inputOne.setText(text.substring(0, text.length() - 1));
        }
    }

    public void eraseAll() {
        inputOne.setText("");
    }
}