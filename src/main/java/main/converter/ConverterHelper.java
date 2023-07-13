package main.converter;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;
import java.util.List;

public class ConverterHelper {
    private static final List<String> currencyValues = Arrays.asList("USD", "EUR", "JPY");
    private static final List<String> areaValues = Arrays.asList("Square meter", "Square kilometer", "Square mile");
    private static final List<String> timeValues = Arrays.asList("Second", "Minute", "Hour");
    private static final List<String> temperatureValues = Arrays.asList("Celsius", "Fahrenheit", "Kelvin");
    private static final List<String> lengthValues = Arrays.asList("Meter", "Kilometer", "Mile");

    private static List<String> getValuesForCategory(String category) {
        return switch (category) {
            case "Currency" -> currencyValues;
            case "Area" -> areaValues;
            case "Time" -> timeValues;
            case "Temperature" -> temperatureValues;
            case "Length" -> lengthValues;
            default -> null;
        };
    }

    public static void updateChoiceBoxes(String category, ChoiceBox<String> choiceOne, ChoiceBox<String> choiceTwo, Label valueOne, Label valueTwo) {
        List<String> values = getValuesForCategory(category);
        assert values != null;
        choiceOne.setItems(FXCollections.observableArrayList(values));
        choiceTwo.setItems(FXCollections.observableArrayList(values));
        choiceOne.getSelectionModel().selectFirst();
        choiceTwo.getSelectionModel().selectNext();
        valueOne.setText(choiceOne.getValue());
        valueTwo.setText(choiceTwo.getValue());
    }

    public static void onChange(ChoiceBox<String> choice, Label value) {
        String selectedValue = choice.getValue();
        value.setText(selectedValue);
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