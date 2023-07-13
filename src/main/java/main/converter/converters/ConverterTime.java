package main.converter.converters;

public class ConverterTime implements Converter {
    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        switch (from) {
            case "Second":
                switch (to) {
                    case "Second" -> result = value;
                    case "Minute" -> result = value / 60;
                    case "Hour" -> result = value / 3600;
                }
            case "Minute":
                switch (to) {
                    case "Second" -> result = value * 60;
                    case "Minute" -> result = value;
                    case "Hour" -> result = value / 60;
                }
            case "Hour":
                switch (to) {
                    case "Second" -> result = value * 3600;
                    case "Minute" -> result = value * 60;
                    case "Hour" -> result = value;
                }
        }
        return result;
    }
}
