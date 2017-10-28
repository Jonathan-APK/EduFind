package controller;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class used the Google Distance Matrix API to calculate the distance by car between two post codes.
 *
 * @author Minions
 * @version 1.0
 * @since 2017-09-27
 */

public class DistanceCalculator extends AsyncTask<String, Void, Double> {

    /**
     * Takes a string array of pin-codes and asynchronously calls the Google distance matric api
     * to calculate the distance between them
     * @param strings (array of strings, consisting the pincode of two address for which the distance is to be determined)
     * @return Distance between the two input pincodes
     */
    @Override
    protected Double doInBackground(String... strings) {
        String pincode_1 = strings[0];
        String pincode_2 = strings[1];

        try {
            String key = "AIzaSyAk87srcPHP5Zo6jhWpdIiSRS-A47jM1eE";
            String s = String.format("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=%s&destinations=%s&key=%s", pincode_1, pincode_2, key);
            URL url = new URL(s);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                Scanner sc = new Scanner(urlConnection.getInputStream());
                StringBuilder sb = new StringBuilder();
                while (sc.hasNext())
                    sb.append(sc.next());
                String json = sb.toString();
                JSONObject obj = new JSONObject(json);
                double distance = obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getDouble("value");
                urlConnection.disconnect();
                sc.close();
                return distance;
            }
        } catch (Exception e) {
            Log.e("DistanceError", "Error ", e);
            return null;
        }
        return null;
    }
}
