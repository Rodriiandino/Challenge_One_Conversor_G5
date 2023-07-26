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
import main.converter.measuremenUnit.MeasurementUnit;

import java.net.URL;
import java.util.ResourceBundle;

/***
 * @author Rodrigo Agustin Andino
 * @version 1.0
 * @apiNote Esta clase es el controlador de la aplicacion de conversiones
 * */

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
    private ChoiceBox<MeasurementUnit> choiceTwo;
    @FXML
    private ChoiceBox<MeasurementUnit> choiceOne;
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

    ConverterCurrency converterCurrency = new ConverterCurrency();
    ConverterArea converterArea = new ConverterArea();
    Converter converterTime = new ConverterTime();
    ConverterTemperature converterTemperature = new ConverterTemperature();
    ConverterLength converterLength = new ConverterLength();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConverterHelper.updateChoiceBoxes("Currency", choiceOne, choiceTwo, valueOne, valueTwo);
       setupListeners();
    }

    private void setupListeners() {
        inputOne.addEventFilter(KeyEvent.KEY_TYPED, ConverterHelper::filterInput);
        choiceOne.setOnAction(event -> valueOne.setText(choiceOne.getValue().longName()));
        choiceTwo.setOnAction(event -> valueTwo.setText(choiceTwo.getValue().longName()));
        choiceOne.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> onChangeInput());
        choiceTwo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> onChangeInput());
        inputOne.textProperty().addListener((observableValue, oldValue, newValue) -> onChangeInput());

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
        String from = choiceOne.getValue().shortName();
        String to = choiceTwo.getValue().shortName();

        if (text.isEmpty() || from == null || to == null) {
            inputTwo.setText("");
            return;
        }

        double result = 0;

        double value = Double.parseDouble(text);
        switch (titleCategory.getText()) {
            case "Currency" -> result = converterCurrency.convert(value, from, to);
            case "Area" -> result = converterArea.convert(value, from, to);
            case "Time" -> result = converterTime.convert(value, from, to);
            case "Temperature" -> result = converterTemperature.convert(value, from, to);
            case "Length" -> result = converterLength.convert(value, from, to);
        }
        if (result % 1 == 0) {
            int intResult = (int) result;
            inputTwo.setText(String.valueOf(intResult));
        } else {
            inputTwo.setText(String.valueOf(result));
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