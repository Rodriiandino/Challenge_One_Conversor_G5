package main.converter.converters;

/***
 * @author Rodrigo Agustin Andino
 * @version 1.0
 * @apiNote Esta clase implementa la interfaz Converter y se encarga de realizar la conversion de temperatura
 * */

public class ConverterTemperature implements Converter {
    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        switch (from) {
            case "°C" -> {
                switch (to) {
                    case "°C" -> result = value;
                    case "°F" -> result = value * 9 / 5 + 32;
                    case "K" -> result = value + 273.15;
                }
            }
            case "°F" -> {
                switch (to) {
                    case "°C" -> result = (value - 32) * 5 / 9;
                    case "°F" -> result = value;
                    case "K" -> result = (value - 32) * 5 / 9 + 273.15;
                }
            }
            case "K" -> {
                switch (to) {
                    case "°C" -> result = value - 273.15;
                    case "°F" -> result = (value - 273.15) * 9 / 5 + 32;
                    case "K" -> result = value;
                }
            }
        }
        return result;
    }
}
