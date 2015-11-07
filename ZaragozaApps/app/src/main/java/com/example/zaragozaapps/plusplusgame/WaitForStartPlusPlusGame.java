package com.example.zaragozaapps.plusplusgame;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.zaragozaapps.matchpaisgame.MatchPairsActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by alber on 06/11/2015.
 */
public class WaitForStartPlusPlusGame extends AsyncTask<String,Void,String> {

    /**
     * The player is waiting for others players. When eveerybody it's ready,
     * the game will start
     */
    private Context context;
    private ProgressDialog dialog;

    private boolean end = false;
    private String id;

    //flag 0 means get and 1 means post.(By default it is get.)
    public WaitForStartPlusPlusGame(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {

        String result = null;
        id = arg0[0];
        boolean begin = false;
        while (!begin) {
            try {
                String link = "http://192.168.10.36:8081/MathWaitPlayers";

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

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
                Log.e("Respuesta: ", result);
                if (result.equalsIgnoreCase("start")) {
                    begin = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return new String("Exception: " + e.getMessage());
            }
        }

        return result;

    }

    @Override
    protected void onPostExecute(String result) {
        Intent i = new Intent(context.getApplicationContext(), PlusPlusActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("id", id);
        context.startActivity(i);
    }

}

