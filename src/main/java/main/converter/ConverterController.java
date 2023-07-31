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
import main.converter.measuremenUnit.ValuesConstants;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        initializeCurrencyValues();
        ConverterHelper.updateChoiceBoxes("Currency", choiceOne, choiceTwo, valueOne, valueTwo);
       setupListeners();
    }

    // Agrega los listeners a los componentes de la interfaz
    private void setupListeners() {
        inputOne.addEventFilter(KeyEvent.KEY_TYPED, ConverterHelper::filterInput);
        choiceOne.setOnAction(event -> ConverterHelper.onChange(choiceOne, valueOne));
        choiceTwo.setOnAction(event -> ConverterHelper.onChange(choiceTwo, valueTwo));
        choiceOne.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> onChangeInput());
        choiceTwo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> onChangeInput());
        inputOne.textProperty().addListener((observableValue, oldValue, newValue) -> onChangeInput());

    }

    // Inicializa los valores de las monedas
    private void initializeCurrencyValues() {
        ConverterCurrency converterCurrency = new ConverterCurrency();
        List<ConverterCurrency.Currency> currencyList = converterCurrency.getCurrencyList();

        List<MeasurementUnit> currencyValues = new ArrayList<>();
        for (ConverterCurrency.Currency currency : currencyList) {
            MeasurementUnit measurementUnit = new MeasurementUnit(currency.getCode(), currency.getName());
            currencyValues.add(measurementUnit);
        }
        ValuesConstants.currencyValues = currencyValues;
    }

    // Selecciona la categoria de conversion
    public void selectCategory(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String text = sourceButton.getText();

        ConverterHelper.clearButtonStyles(btnCurrency, btnArea, btnTime, btnTemperature, btnLength);

        sourceButton.getStyleClass().add("button-active");
        titleCategory.setText(text);

        ConverterHelper.updateChoiceBoxes(text, choiceOne, choiceTwo, valueOne, valueTwo);
    }

    // Agrega un nuevo valor al inputOne segun el boton presionado
    public void newValue(ActionEvent event) {
        String text = inputOne.getText();
        Button sourceButton = (Button) event.getSource();
        String number = sourceButton.getText();

        if (number.equals(".") && text.contains(".")) {
            return;
        } else if (number.equals(".") && text.isEmpty()) {
            text = "0";
        } else if (text.equals("0") && !number.equals(".")) {
            text = "";
        }

        inputOne.setText(text + number);
    }

    // Convierte el valor del inputOne al inputTwo segun la categoria seleccionada
    public void onChangeInput() {

        MeasurementUnit selectedUnitOne = choiceOne.getValue();
        MeasurementUnit selectedUnitTwo = choiceTwo.getValue();

        if (selectedUnitOne == null || selectedUnitTwo == null) {
            inputTwo.setText("");
            return;
        }

        String text = inputOne.getText();
        String from = selectedUnitOne.shortName();
        String to = selectedUnitTwo.shortName();

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

    // Borra el ultimo caracter del input
    public void eraseLast() {
        String text = inputOne.getText();
        if (!text.isEmpty()) {
            inputOne.setText(text.substring(0, text.length() - 1));
        }
    }

    // Borra el texto del input
    public void eraseAll() {
        inputOne.setText("");
    }
}