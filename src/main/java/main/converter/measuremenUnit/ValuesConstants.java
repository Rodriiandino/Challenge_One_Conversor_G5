package main.converter.measuremenUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * @author Rodrigo Agustin Andino
 * @version 1.0
 * @apiNote Clase que contiene los valores de las unidades de medida
 * */

public class ValuesConstants {
    public static List<MeasurementUnit> currencyValues = new ArrayList<>();

    public static final List<MeasurementUnit> areaValues = Arrays.asList(
            new MeasurementUnit("M2", "Square meter"),
            new MeasurementUnit("KM2", "Square kilometer"),
            new MeasurementUnit("MILE2", "Square mile")
    );

    public static final List<MeasurementUnit> timeValues = Arrays.asList(
            new MeasurementUnit("S", "Second"),
            new MeasurementUnit("M", "Minute"),
            new MeasurementUnit("H", "Hour")
    );

    public static final List<MeasurementUnit> temperatureValues = Arrays.asList(
            new MeasurementUnit("°C", "Celsius"),
            new MeasurementUnit("°F", "Fahrenheit"),
            new MeasurementUnit("K", "Kelvin")
    );

    public static final List<MeasurementUnit> lengthValues = Arrays.asList(
            new MeasurementUnit("M", "Meter"),
            new MeasurementUnit("KM", "Kilometer"),
            new MeasurementUnit("Mi", "Mile"),
            new MeasurementUnit("CM", "Centimeter"),
            new MeasurementUnit("MM", "Millimeter")
    );

}

