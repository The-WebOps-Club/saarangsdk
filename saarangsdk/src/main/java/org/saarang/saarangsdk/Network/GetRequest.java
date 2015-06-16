package org.saarang.saarangsdk.Network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ahammad on 15/06/15.
 */
public class GetRequest {

    private static final String LOG_TAG = "GetRequest";

    public static JSONObject execute(String mUrl, String token) throws JSONException {

        //Creating dummy response
        String responseBody;
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 980);

        try {
            // URL get Request
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Set header if token is given
            if (token != null){
                conn.setRequestProperty ("Authorization", "Bearer " + token);
            }

            // Include responsecode form server in josnResponse
            jsonResponse.put("status", conn.getResponseCode());

            // Terminate the process if response code is not 200 or OK
            if (conn.getResponseCode() != 200) {
                conn.disconnect();
                return jsonResponse;
            }

            // Processing the response
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            StringBuilder sb = new StringBuilder();
            responseBody = "";
            while ((output = br.readLine()) != null) {
                sb.append(output);
                responseBody = sb.toString();
                Log.d(LOG_TAG, responseBody);
                jsonResponse.put("data", new JSONObject(responseBody));
            }
            conn.disconnect();
        } catch (JSONException e){
            e.printStackTrace();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }


        return jsonResponse;
    }
}
