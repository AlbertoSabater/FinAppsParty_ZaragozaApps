package com.example.zaragozaapps.touchgame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaragozaapps.zaragozaapps.MainActivity;
import com.example.zaragozaapps.zaragozaapps.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by alber on 06/11/2015.
 */
public class BackGroundInitTouchGame extends AsyncTask<String,Void,String> {

    /**
     * The player is signed up in the game and he will wait for others players
     */

    private Context context;

    private boolean end = false;
    private String id;

    //flag 0 means get and 1 means post.(By default it is get.)
    public BackGroundInitTouchGame(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        Log.e("TOUCH GAME", "BackGroundInitTouchGame");

        String result = null;
        id = arg0[0];
        try {
            String link = "http://192.168.10.36:8081/Init";

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            Log.e("TOUCH GAME", "1");

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            JSONObject json = new JSONObject();
            json.put("id", id);

            wr.write(json.toString());
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            result = sb.toString();
            wr.close();
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            return new String("Exception: " + e.getMessage());
        }

        return result;

    }

    @Override
    protected void onPostExecute(String result) {
        Log.e("TOUCH GAME", "Starting BackGroundInitTouchGame");
        new WaitForStartTouchGame(context).execute(id);
    }

}

