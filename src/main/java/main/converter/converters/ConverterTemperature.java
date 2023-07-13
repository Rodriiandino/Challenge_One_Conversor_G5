package main.converter.converters;

public class ConverterTemperature implements Converter {
    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        switch (from) {
            case "Celsius":
                switch (to) {
                    case "Celsius" -> result = value;
                    case "Fahrenheit" -> result = value * 9 / 5 + 32;
                    case "Kelvin" -> result = value + 273.15;
                }
            case "Fahrenheit":
                switch (to) {
                    case "Celsius" -> result = (value - 32) * 5 / 9;
                    case "Fahrenheit" -> result = value;
                    case "Kelvin" -> result = (value - 32) * 5 / 9 + 273.15;
                }
            case "Kelvin":
                switch (to) {
                    case "Celsius" -> result = value - 273.15;
                    case "Fahrenheit" -> result = (value - 273.15) * 9 / 5 + 32;
                    case "Kelvin" -> result = value;
                }
        }
        return result;
    }
}
