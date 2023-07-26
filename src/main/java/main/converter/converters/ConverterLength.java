package main.converter.converters;

/***
 * @author Rodrigo Agustin Andino
 * @version 1.0
 * @apiNote Esta clase implementa la interfaz Converter y se encarga de realizar la conversion de unidades de longitud
 * */

public class ConverterLength implements Converter {
    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        switch (from) {
            case "M" -> {
               switch (to) {
                   case "M" -> result = value;
                   case "KM" -> result = value/ 1000;
                   case "Mi" -> result = value/ 1609.344;
                   case "CM" -> result = value * 100;
                   case "MM" -> result = value * 1000;
               }
            }
            case "KM" -> {
                switch (to) {
                    case "M" -> result = value * 1000;
                    case "KM" -> result = value;
                    case "Mi" -> result = value / 1.609;
                    case "CM" -> result = value * 100000;
                    case "MM" -> result = value * 1000000;
                }
            }
            case "Mi" -> {
                 switch (to) {
                 case "M" -> result = value * 1609.344;
                 case "KM" -> result = value * 1.609;
                 case "Mi" -> result = value;
                 case "CM" -> result = value * 160934.4;
                 case "MM" -> result = value * 1609344;
                 }
            }
            case "CM" -> {
                switch (to) {
                    case "M" -> result = value / 100;
                    case "KM" -> result = value / 100000;
                    case "Mi" -> result = value / 160934.4;
                    case "CM" -> result = value;
                    case "MM" -> result = value / 10;
                }
            }
            case "MM" -> {
                switch (to) {
                    case "M" -> result = value / 1000;
                    case "KM" -> result = value / 1000000;
                    case "Mi" -> result = value / 1609344;
                    case "CM" -> result = value * 10;
                    case "MM" -> result = value;
                }
            }
        }
        return result;
    }
}
