package main.converter.converters;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/***
 * @author Rodrigo Agustin Andino
 * @version 1.0
 * @apiNote Esta clase implementa la interfaz Converter y se encarga de realizar la conversion de monedas
 * */

public class ConverterCurrency implements Converter {

    private final String API_URL = "https://api.apilayer.com/currency_data/convert";

    private final String API_URL_LIST = "https://api.apilayer.com/currency_data/list";
    private final String API_KEY = "nP50g2DO7j7I45tRlmMvbSWBYPcpxNNu";

    // Pasos para hacer una peticion a una API
    @Override
    public double convert(double value, String from, String to) {
        double result = 0;
        try {
            // 1. Crear una nueva instancia de URI con la URL de la API y los parámetros de consulta.
            URI uri = new URI(API_URL + "?to=" + to + "&from=" + from + "&amount=" + value);
            // 2. Convertir la URI a una URL para poder abrir una conexión HTTP.
            URL url = uri.toURL();
            // 3. Abrir una conexión HTTP a la URL proporcionada.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 4. Establecer el método de solicitud como GET.
            connection.setRequestMethod("GET");
            // 5. Agregar el encabezado 'apikey' con el valor del API_KEY a la solicitud.
            connection.setRequestProperty("apikey", API_KEY);

            // 6. Obtener el código de respuesta de la solicitud HTTP.
            int responseCode = connection.getResponseCode();

            // 7. Si el código de respuesta es 200, leer la respuesta de la solicitud.
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 8. Leer la respuesta del servidor línea por línea y almacenarla en un StringBuilder.
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                // 9. Cerrar el lector.
                reader.close();

                // 10. Analizar la respuesta JSON y devolver el resultado.
                result = parseJSON(response.toString());
            } else {
                // 11. Si el código de respuesta no es 200, imprimir un mensaje de error.
                System.out.println("Error en la solicitud: " + responseCode);
            }
        } catch (IOException | URISyntaxException e) {
            // 12. Si ocurre una excepción, imprimir el mensaje de error.
            e.printStackTrace();
        }
        return result;
    }

    private double parseJSON(String json) {
        double resultValue = 0;
        try {
            Gson gson = new Gson();
            // Analizar el JSON y obtener el valor de 'result'.
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);


            // Verificar si la clave "result" está presente en el JSON y no es nula.
            if (jsonObject.has("result") && !jsonObject.get("result").isJsonNull()) {
                // Obtener el valor de 'result' como un double.
                resultValue = jsonObject.get("result").getAsDouble();
            } else {
                // Si la clave "result" no está presente o es nula, asignar el valor predeterminado (0).
                resultValue = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultValue;
    }


    // Método para obtener la lista de monedas desde la API.
    public List<Currency> getCurrencyList() {
        List<Currency> currencyList = new ArrayList<>();
        try {
            URI uri = new URI(API_URL_LIST);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey", API_KEY);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                currencyList = parseCurrencyListJSON(response.toString());
                System.out.println(currencyList);
            } else {
                System.out.println("Error en la solicitud: " + responseCode);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return currencyList;
    }

    // Método para analizar la respuesta JSON y obtener la lista de monedas.
    private List<Currency> parseCurrencyListJSON(String json) {
        List<Currency> currencyList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            if (jsonObject.has("currencies")) {
                JsonObject currenciesObject = jsonObject.getAsJsonObject("currencies");
                for (Map.Entry<String, JsonElement> entry : currenciesObject.entrySet()) {
                    String code = entry.getKey();
                    String name = entry.getValue().getAsString();
                    Currency currency = new Currency(code, name);
                    currencyList.add(currency);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyList;
    }

    // Clase interna para representar una moneda.
    public static class Currency {
        private String code;
        private String name;

        public Currency(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return code + " - " + name;
        }
    }

}




