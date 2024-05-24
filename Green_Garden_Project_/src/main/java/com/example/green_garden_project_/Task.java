package com.example.green_garden_project_;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Task extends Item {
    private LocalDate dateTask;

    public Task(String dateTask) {
        super("");
        // chatGPT Valider le format de la date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            this.dateTask = LocalDate.parse(dateTask, formatter);
        } catch (Exception e) {
            System.err.println("Format de date incorrect. Utilisez le format dd/MM/yyyy.");
            return; // Sortir du constructeur si le format de la date est incorrect
        }
    }

    public Task(LocalDate dateTask) {
        super("");
        this.dateTask = dateTask;
    }

    public LocalDate getDateTask() {
        return dateTask;
    }

    public void setDateTask(LocalDate dateTask) {
        this.dateTask = dateTask;
    }

    // chatGPT
    public void setDateTask(String dateTask) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            this.dateTask = LocalDate.parse(dateTask, formatter);
        } catch (Exception e) {
            System.err.println("Format de date incorrect. Utilisez le format dd/MM/yyyy.");
        }
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Date : ").append(dateTask).append(", ");

        // Get weather data for Paris at dateTask
        JSONObject weatherData = getWeatherData("Orsay", dateTask);
        if (weatherData != null) {
            result.append("Temperature: ").append(weatherData.get("temperature")).append("°C, ");
            result.append("Humidité Relative: ").append(weatherData.get("relative_humidity")).append(", ");
            result.append("Vitesse du vent: ").append(weatherData.get("wind_speed")).append("m/s");
        } else {
            result.append("Error: Could not fetch weather data for Paris on ").append(dateTask).append("\n");
        }

        return result.toString();
    }

    //https://www.youtube.com/watch?v=WS_H44tvZMI
    private JSONObject getWeatherData(String city, LocalDate date) {
        try {
            JSONObject cityLocationData = getLocationData(city);
            if (cityLocationData == null) {
                System.out.println("Error: Could not get location data for city " + city);
                return null;
            }

            double latitude = (double) cityLocationData.get("latitude");
            double longitude = (double) cityLocationData.get("longitude");

            // Format the date to match API's format (YYYY-MM-DD)
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                    "&longitude=" + longitude + "&date=" + formattedDate + "&current=temperature_2m,relative_humidity_2m,wind_speed_10m";
            HttpURLConnection apiConnection = fetchApiResponse(url);

            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }

            String jsonResponse = readApiResponse(apiConnection);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            JSONObject currentWeatherJson = (JSONObject) jsonObject.get("current");

            JSONObject weatherData = new JSONObject();
            weatherData.put("temperature", currentWeatherJson.get("temperature_2m"));
            weatherData.put("relative_humidity", currentWeatherJson.get("relative_humidity_2m"));
            weatherData.put("wind_speed", currentWeatherJson.get("wind_speed_10m"));

            return weatherData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    private JSONObject getLocationData(String city){
        city = city.replaceAll(" ", "+");

        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                city + "&count=1&language=en&format=json";

        try {
            // Fetch the API response based on API Link
            HttpURLConnection apiConnection = fetchApiResponse(urlString);

            // check for response status
            // 200 - means that the connection was a success
            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }

            // Read the response and convert store String type
            String jsonResponse = readApiResponse(apiConnection);

            // Parse the string into a JSON Object
            JSONParser parser = new JSONParser();
            JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);

            // Retrieve Location Data
            JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
            return (JSONObject) locationData.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String readApiResponse(HttpURLConnection apiConnection) {
        try {
            // Create a StringBuilder to store the resulting JSON data
            StringBuilder resultJson = new StringBuilder();

            // Create a Scanner to read from the InputStream of the HttpURLConnection
            Scanner scanner = new Scanner(apiConnection.getInputStream());

            // Loop through each line in the response and append it to the StringBuilder
            while (scanner.hasNext()) {
                // Read and append the current line to the StringBuilder
                resultJson.append(scanner.nextLine());
            }

            // Close the Scanner to release resources associated with it
            scanner.close();

            // Return the JSON data as a String
            return resultJson.toString();

        } catch (IOException e) {
            // Print the exception details in case of an IOException
            e.printStackTrace();
            return null;
        }
    }

    private static HttpURLConnection fetchApiResponse(String urlString){
        try{
            // attempt to create connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // set request method to get
            conn.setRequestMethod("GET");

            return conn;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> dates = new ArrayList<>();
        dates.add("01/05/2024");
        dates.add("02/05/2024");
        dates.add("03/05/2024");

        for (String dateStr : dates) {
            Task task = new Task(dateStr);
            System.out.println(task);
            System.out.println("--------------------");
        }
    }


}
