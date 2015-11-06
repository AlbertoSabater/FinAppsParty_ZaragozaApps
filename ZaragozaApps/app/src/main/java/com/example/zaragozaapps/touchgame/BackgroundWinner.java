package com.example.zaragozaapps.touchgame;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
public class BackgroundWinner extends AsyncTask<String,Void,String> {

    private Context context;

    private boolean end = false;

    //flag 0 means get and 1 means post.(By default it is get.)
    public BackgroundWinner(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {

        String result = null;
        while (!end) {
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

                result = sb.toString();

                if (!result.equalsIgnoreCase("wait") && result != null) {
                    end = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return new String("Exception: " + e.getMessage());
            }

        }

        return result;

    }

    @Override
    protected void onPostExecute(String result){

        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialogendofgame);

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.tvGameResult);
        text.setText(result);

        Button dialogButton = (Button) dialog.findViewById(R.id.btGameResult);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(i);
            }
        });

        dialog.show();

    }

}
