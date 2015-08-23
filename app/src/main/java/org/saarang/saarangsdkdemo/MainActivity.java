package org.saarang.saarangsdkdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;
import org.saarang.saarangsdk.Network.HttpRequest;

public class MainActivity extends AppCompatActivity {

    public static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                Log.d(LOG_TAG, "Started");
                String url = "http://10.21.209.192:9000/api/events/55ba455abeb604f1066c3122";
                String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWQ3MjAzNGYwYTcyMmM0MDdiNThjNTYiLCJpYXQiOjE0NDAyMTk1MjI3NTUsImV4cCI6MTQ0MDIyMTMyMjc1NX0.KR-8Oh-vyZPP7Wy0UnH1_442zL_c7vD9wgUZyBDWwXk";
                JSONObject jsonParam;
                try {
                    jsonParam = new JSONObject("{\n" +
                            "    \"name\": \"webops\",\n" +
                            "    \"time\": \"2015-07-30T05:16:06.000Z\",\n" +
                            "    \"description\" : \"Club for web and mobile developers\",\n" +
                            "    \"venue\"  : \"Himalaya Lawns\",\n" +
                            "    \"deatails\" : \"This is an event for andriod developers\",\n" +
                            "    \"coords\":[{\n" +
                            "          \"name\": \"Aqel ahammad K P\",\n" +
                            "          \"phoneNumber\": \"9633229144\",\n" +
                            "          \"email\": \"aqel123@gmail.com\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"name\": \"Aravind Manoj\",\n" +
                            "          \"phoneNumber\": \"9846929348\",\n" +
                            "          \"email\": \"aravind@gmail.com\"\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}");
                    JSONObject json = HttpRequest.execute("PUT", url, token, jsonParam);
                    Log.d(LOG_TAG, json.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
