package main.converter.measuremenUnit;

import javafx.util.StringConverter;


/***
 * @autor Rodrigo Agustin Andino
 * @version 1.0
 * @apiNote Clase que convierte los valores de las unidades de medida a String
 * */

public class MeasurementUnitStringConverter extends StringConverter<MeasurementUnit> {
    @Override
    public String toString(MeasurementUnit measurementUnit) {

        if (measurementUnit == null) {
            return "";
        }

        return measurementUnit.shortName();
    }

    @Override
    public MeasurementUnit fromString(String string) {
        return null;
    }
}
