package controller;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * This class used the Google Maps API to find the latitude and longitude for a given address.
 *
 * @author Minions
 * @version 1.0
 * @since 2017-10-24
 */

public class MapController extends AsyncTask<String, Void, double[]> {

    /**
     * Takes a pincode and calls the Google maps api to find the latitude and longitude for that address
     * @param strings (pincode of the address)
     * @return double array consisting of the latitude and longitude of the address specified by the input pincode
     */
    @Override
    protected double[] doInBackground(String... strings) {
        String address = strings[0];
        try {
            String s = String.format("http://maps.google.com/maps/api/geocode/json?address=%s", address);
            Log.d("url", s);
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
                JSONObject location = obj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                double loglat[] = {location.getDouble("lat"), location.getDouble("lng")};
                urlConnection.disconnect();
                sc.close();
                return loglat;
            }
        } catch (Exception e) {
            Log.e("LocationError", "Error ", e);
            return null;
        }
        return null;
    }
}
