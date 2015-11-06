package com.example.zaragozaapps.touchgame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaragozaapps.zaragozaapps.MainActivity;
import com.example.zaragozaapps.zaragozaapps.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by alber on 06/11/2015.
 */
public class SendTouchs extends AsyncTask<String,Void,String> {

    private Context context;

    private boolean end = false;

    //flag 0 means get and 1 means post.(By default it is get.)
    public SendTouchs(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {

            try {
                String link = "http://192.168.10.36/phpdocs/hello.php";

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

                String result = sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return new String("Exception: " + e.getMessage());
            }



        return null;

    }

    @Override
    protected void onPostExecute(String result){

        // Nothing to do

    }

}
