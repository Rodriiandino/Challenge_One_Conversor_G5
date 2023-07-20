package main.converter.converters;

public class ConverterCurrency implements Converter {
    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        switch (from) {
            case "USD" -> {
               switch (to) {
                   case "USD" -> result = value;
                   case "EUR" -> result = value * 0.85;
                   case "JPY" -> result = value * 0.72;
               }
            }
            case "EUR" -> {
                switch (to) {
                    case "USD" -> result = value * 1.18;
                    case "EUR" -> result = value;
                    case "JPY" -> result = value * 0.85;
                }
            }
            case "JPY" -> {
                 switch (to) {
                 case "USD" -> result = value * 1.39;
                 case "EUR" -> result = value * 1.17;
                 case "JPY" -> result = value;
                 }
            }
        }
        return result;
    }
}
