package main.converter.converters;

public class ConverterTime extends Converter {
    public double convert(double value, String from, String to) {
        switch (from) {
            case "Second":
                switch (to) {
                    case "Second" -> {
                        return value;
                    }
                    case "Minute" -> {
                        return value / 60;
                    }
                    case "Hour" -> {
                        return value / 3600;
                    }
                }
            case "Minute":
                switch (to) {
                    case "Second" -> {
                        return value * 60;
                    }
                    case "Minute" -> {
                        return value;
                    }
                    case "Hour" -> {
                        return value / 60;
                    }
                }
            case "Hour":
                switch (to) {
                    case "Second" -> {
                        return value * 3600;
                    }
                    case "Minute" -> {
                        return value * 60;
                    }
                    case "Hour" -> {
                        return value;
                    }
                }
        }
        return 0;
    }
}
