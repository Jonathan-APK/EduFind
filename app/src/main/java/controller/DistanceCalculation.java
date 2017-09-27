package controller;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.example.utsav.edufind.R;

import org.json.JSONObject;

import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by utsav on 27/9/17.
 */

public class DistanceCalculation extends AsyncTask<String, Void, Double>{
    @Override
    protected Double doInBackground(String... strings){
        String pincode_1 = strings[0];
        String pincode_2 = strings[1];

        try{
            // To Do: find way to secure the key
            String key = "AIzaSyA4weAen8iFGCIl_RxzGmFEodGV-YXPVFw";
            String s = String.format("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=%s&destinations=%s&key=%s",
                    pincode_1, pincode_2, key);
            System.out.println(s);
            URL url = new URL(s);
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int statusCode=urlConnection.getResponseCode();
            if(statusCode==HttpURLConnection.HTTP_OK) {
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
        }
        catch (Exception e){
            Log.e("DistanceError", "Error ", e);
            return null;
        }
        return null;
    }
}
