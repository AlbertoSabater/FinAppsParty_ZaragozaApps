package com.example.zaragozaapps.matchpaisgame;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.zaragozaapps.touchgame.WaitForStartTouchGame;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sergiolazaromagdalena on 7/11/15.
 */
public class MatchPairsSendButton extends AsyncTask<String,Void,String> {

    /**
     * The player is signed up in the game and he will wait for others players
     */

    private Context context;

    private String id, x, y;

    //flag 0 means get and 1 means post.(By default it is get.)
    public MatchPairsSendButton(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {

        String result = null;
        id = arg0[0];
        x = arg0[1];
        y = arg0[2];
        try {
            String link = "http://192.168.10.36:8081/pushButton";

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("x",x);
            json.put("y",y);

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

    }
}
