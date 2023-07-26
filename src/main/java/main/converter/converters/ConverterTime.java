package main.converter.converters;

/***
 * @author Rodrigo Agustin Andino
 * @version 1.0
 * @apiNote Esta clase implementa la interfaz Converter y se encarga de realizar la conversion de Tiempo
 * */

public class ConverterTime implements Converter {
    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        switch (from) {
            case "S" -> {
                switch (to) {
                    case "S" -> result = value;
                    case "M" -> result = value / 60;
                    case "H" -> result = value / 3600;
                }
            }
            case "M" -> {
                switch (to) {
                    case "S" -> result = value * 60;
                    case "M" -> result = value;
                    case "H" -> result = value / 60;
                }
            }
            case "H" -> {
                switch (to) {
                    case "S" -> result = value * 3600;
                    case "M" -> result = value * 60;
                    case "H" -> result = value;
                }
            }
        }
        return result;
    }
}
