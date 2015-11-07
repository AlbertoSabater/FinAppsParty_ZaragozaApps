package com.example.zaragozaapps.zaragozaapps;

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
import java.util.ArrayList;

/**
 * Created by alber on 07/11/2015.
 */
public class GetRanking extends AsyncTask<String,Void,String> {

    /**
     * The player is signed up in the game and he will wait for others players
     */

    private Context context;

    //flag 0 means get and 1 means post.(By default it is get.)
    public GetRanking(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {

        String result = null;
        try {
            String link = "http://192.168.10.36/phpdocs/getRanking.php";

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write("");
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
        String[] data = result.split(",");
        Log.e("RANKING", "Parsing data " + data.length);

        ArrayList<UserPoints> up = new ArrayList<UserPoints>();
        for (int i=0; i<data.length-1; i=i+2) {
            up.add(new UserPoints(data[i], data[i+1]));
        }

        Ranking.setArray(up);
        Ranking.adapter = new RankingAdapter(context, up);
        Ranking.listview.setAdapter(Ranking.adapter);
        Ranking.fillData();
        Log.e("RANKING", "Parsing data " + up.size());

    }

}

