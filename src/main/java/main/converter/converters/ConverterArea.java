package main.converter.converters;

public class ConverterArea implements Converter {
    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        switch (from) {
            case "Square meter" -> {
                switch (to) {
                    case "Square meter" -> result = value;
                    case "Square kilometer" -> result = value / 1000000;
                    case "Square mile" -> result = value / 2590000;
                }
            }
            case "Square kilometer" -> result = switch (to) {
                case "Square meter" -> value * 1000000;
                case "Square kilometer" -> value;
                case "Square mile" -> value / 2.59;
                default -> result;
            };
            case "Square mile" -> result = switch (to) {
                case "Square meter" -> value * 2590000;
                case "Square kilometer" -> value * 2.59;
                case "Square mile" -> value;
                default -> result;
            };
        }
        return result;
    }
}
