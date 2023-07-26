package main.converter;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.util.List;

import main.converter.measuremenUnit.MeasurementUnit;
import main.converter.measuremenUnit.ValuesConstants;

/***
 * @author Rodrigo Agustin Andino
 * @version 1.0
 * @apiNote Esta clase se encarga de manejar los eventos de los botones y de los choiceBox de la interfaz gráfica
 * */

public class ConverterHelper {

    private static List<MeasurementUnit> getValuesForCategory(String category) {
        return switch (category) {
            case "Currency" -> ValuesConstants.currencyValues;
            case "Area" -> ValuesConstants.areaValues;
            case "Time" -> ValuesConstants.timeValues;
            case "Temperature" ->  ValuesConstants.temperatureValues;
            case "Length" -> ValuesConstants.lengthValues;
            default -> null;
        };
    }

    public static void updateChoiceBoxes(String category, ChoiceBox<MeasurementUnit> choiceOne,
                                         ChoiceBox<MeasurementUnit> choiceTwo, Label valueOne, Label valueTwo) {


        List<MeasurementUnit> values = getValuesForCategory(category);

        if (values == null) {
            System.err.println("Error: Invalid category '" + category + "'");
            return;
        }

        choiceOne.setItems(FXCollections.observableArrayList(values));
        choiceTwo.setItems(FXCollections.observableArrayList(values));
        choiceOne.getSelectionModel().selectFirst();
        choiceTwo.getSelectionModel().select(1);
        valueOne.setText(choiceOne.getValue().longName());
        valueTwo.setText(choiceTwo.getValue().longName());
    }


    public static void clearButtonStyles(Button... buttons) {
        for (Button button : buttons) {
            button.getStyleClass().remove("button-active");
        }
    }

    public static void filterInput(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        if (!Character.isDigit(c) && c != '.') {
            event.consume();
        }
    }

}