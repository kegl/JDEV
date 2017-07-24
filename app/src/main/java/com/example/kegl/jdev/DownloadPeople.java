package com.example.kegl.jdev;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by kegl on 177/6/.
 */

public class DownloadPeople extends AsyncTask<String, Void, ArrayList<People>> {

    Home screen;
    HttpURLConnection urlConnection;
    final String MY_URL = "http://www.iut-adouretud.univ-pau.fr/~olegoaer/webservices/jdev2017.php";

    public DownloadPeople(Home screen) {
        this.screen = screen;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<People> doInBackground(String... strings) {
        ArrayList<People> fetchedData = new ArrayList<People>();
        String stream = null;
        try {
            URL url = new URL(MY_URL);
            this.urlConnection = (HttpURLConnection) url.openConnection();
            this.urlConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream((this.urlConnection.getInputStream()));
            BufferedReader r = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line);
            }
            stream = sb.toString();

            JSONArray concepts = new JSONObject(stream).getJSONArray("people");
            for (int i = 0; i < concepts.length(); i++) {
                JSONObject record = concepts.getJSONObject(i);
                fetchedData.add(new People(
                        record.getString("nom"),
                        record.getString("prenom")
                ));
            }
        } catch (Exception e) {
            Log.e("genDROID", "An error occured while fetching", e);
        } finally {
            this.urlConnection.disconnect();
        }
        return fetchedData;
    }

    @Override
    protected void onPostExecute(ArrayList<People> peoples) {
        super.onPostExecute(peoples);
        this.screen.populate(peoples);
    }
}
