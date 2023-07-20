package main.converter.converters;

public class ConverterLength implements Converter {
    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        switch (from) {
            case "Meter" -> {
               switch (to) {
                   case "Meter" -> result = value;
                   case "Kilometer" -> result = value/ 1000;
                   case "Mile" -> result = value/ 1609.344;
               }
            }
            case "Kilometer" -> {
                switch (to) {
                    case "Meter" -> result = value * 1000;
                    case "Kilometer" -> result = value;
                    case "Mile" -> result = value / 1.609;
                }
            }
            case "Mile" -> {
                 switch (to) {
                 case "Meter" -> result = value * 1609.344;
                 case "Kilometer" -> result = value * 1.609;
                 case "Mile" -> result = value;
                 }
            }
        }
        return result;
    }
}
