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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by alber on 06/11/2015.
 */
public class BackgroundWinner extends AsyncTask<String,Void,String> {

    /**
     * Waits for the end of the game, and notufy the players
     */

    private Context context;
    private String id;

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
        id = arg0[0];

        while (!end) {
            try {
                String link = "http://192.168.10.36:8081/won";

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                JSONObject json = new JSONObject();
                json.put("id", id);

                wr.write(json.toString());
                wr.flush();
                wr.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                result = sb.toString();
                Log.e("TOUCH GAME", result);

                if (!result.equalsIgnoreCase("wait") && result != null) {
                    end = true;
                }

                reader.close();
                Thread.sleep(1000);


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
        dialog.setCanceledOnTouchOutside(false);

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.tvGameResult);
        Log.e("TOUCH GAME", "result" + result);

        if(result.equalsIgnoreCase(id)) {
            text.setText("You win!!!");
        }
        else {
            text.setText("You lose.");
        }


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