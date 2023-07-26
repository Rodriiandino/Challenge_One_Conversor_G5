package main.converter.converters;

/***
 * @author Rodrigo Agustin Andino
 * @version 1.0
 * @apiNote Esta clase implementa la interfaz Converter y se encarga de realizar la conversion de areas
 * */

public class ConverterArea implements Converter {

    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        switch (from) {
            case "M2" -> {
                switch (to) {
                    case "M2" -> result = value;
                    case "KM2" -> result = value / 1000000;
                    case "MILE2" -> result = value / 2590000;
                }
            }
            case "KM2" -> result = switch (to) {
                case "M2" -> value * 1000000;
                case "KM2" -> value;
                case "MILE2" -> value / 2.59;
                default -> result;
            };
            case "MILE2" -> result = switch (to) {
                case "M2" -> value * 2590000;
                case "KM2" -> value * 2.59;
                case "MILE2" -> value;
                default -> result;
            };
        }
        return result;
    }
}
