package controller;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.example.utsav.edufind.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by utsav on 27/9/17.
 */

public class DistanceCalculation extends AsyncTask<String, Void, String>{
    @Override
    protected String doInBackground(String... strings){
        String pincode_1 = strings[0];
        String pincode_2 = strings[1];

        try{
            String key = "AIzaSyA4weAen8iFGCIl_RxzGmFEodGV-YXPVFw";
            String s = String.format("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=%s&destinations=%s&key=%s",
                    pincode_1, pincode_2, key);
            System.out.println(s);
            URL url = new URL(s);
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int statuscode=urlConnection.getResponseCode();
            if(statuscode==HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                String json = sb.toString();
                Log.d("JSON", json);
                JSONObject root = new JSONObject(json);
                JSONArray array_rows = root.getJSONArray("rows");
                Log.d("JSON", "array_rows:" + array_rows);
                JSONObject object_rows = array_rows.getJSONObject(0);
                Log.d("JSON", "object_rows:" + object_rows);
                JSONArray array_elements = object_rows.getJSONArray("elements");
                Log.d("JSON", "array_elements:" + array_elements);
                JSONObject object_elements = array_elements.getJSONObject(0);
                Log.d("JSON", "object_elements:" + object_elements);
                JSONObject object_duration = object_elements.getJSONObject("duration");
                JSONObject object_distance = object_elements.getJSONObject("distance");

                Log.d("JSON", "object_duration:" + object_duration);
                urlConnection.disconnect();
                br.close();
                System.out.println(object_distance.getString("value"));
                return object_distance.getString("value");
            }
        }
        catch (Exception e){
            Log.e("MapError", "Error ", e);
            return null;
        }
        return null;
    }
}
