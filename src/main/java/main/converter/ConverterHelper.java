package main.converter;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import main.converter.converters.ConverterCurrency;
import main.converter.measuremenUnit.MeasurementUnit;
import main.converter.measuremenUnit.ValuesConstants;
import main.converter.measuremenUnit.MeasurementUnitStringConverter;

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

    // Actualiza los valores de los choiceBox y los labels de la interfaz
    public static void updateChoiceBoxes(String category, ChoiceBox<MeasurementUnit> choiceOne,
                                         ChoiceBox<MeasurementUnit> choiceTwo, Label valueOne, Label valueTwo) {

        List<MeasurementUnit> values = getValuesForCategory(category);

        if (values == null) {
            System.err.println("Error: Invalid category '" + category + "'");
            return;
        }

        choiceOne.setItems(FXCollections.observableArrayList(values));
        choiceOne.setConverter(new MeasurementUnitStringConverter());

        choiceTwo.setItems(FXCollections.observableArrayList(values));
        choiceTwo.setConverter(new MeasurementUnitStringConverter());

        choiceOne.getSelectionModel().selectFirst();
        choiceTwo.getSelectionModel().select(1);

        MeasurementUnit selectedUnitOne = choiceOne.getValue();
        MeasurementUnit selectedUnitTwo = choiceTwo.getValue();

        if (selectedUnitOne != null) {
            valueOne.setText(selectedUnitOne.longName());
        } else {
            valueOne.setText("");
        }

        if (selectedUnitTwo != null) {
            valueTwo.setText(selectedUnitTwo.longName());
        } else {
            valueTwo.setText("");
        }
    }

    // Actualiza los valores de los choiceBox y los labels de la interfaz
    public static void onChange(ChoiceBox<MeasurementUnit> choiceOne, Label valueOne) {

        MeasurementUnit selectedUnit = choiceOne.getValue();

        if (selectedUnit == null) {
            return;
        }

        valueOne.setText(selectedUnit.longName());
    }

    // Elimina los estilos de los botones
    public static void clearButtonStyles(Button... buttons) {
        for (Button button : buttons) {
            button.getStyleClass().remove("button-active");
        }
    }

    // Filtra el input para que solo se puedan ingresar numeros y el punto
    public static void filterInput(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        String text = ((TextInputControl) event.getSource()).getText();

        if (c == '.' && text.isEmpty()) {
            ((TextInputControl) event.getSource()).setText("0.");
            event.consume();
        } else if (text.equals("0") && Character.isDigit(c)) {
            ((TextInputControl) event.getSource()).setText(String.valueOf(c));
            event.consume();
        } else if (c == '.' && text.contains(".")) {
            event.consume();
        } else if (!Character.isDigit(c) && c != '.') {
            event.consume();
        }
    }
}