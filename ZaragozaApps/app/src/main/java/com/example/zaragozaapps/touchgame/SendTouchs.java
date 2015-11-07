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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by alber on 06/11/2015.
 */
public class SendTouchs extends AsyncTask<String,Void,String> {

    /**
     * Send post with new touchs
     */

    private Context context;
    private String id;
    private String height;

    private boolean end = false;

    //flag 0 means get and 1 means post.(By default it is get.)
    public SendTouchs(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {

        id = arg0[0];
        height = arg0[1];

        try {
            URL url = new URL("http://192.168.10.36:8081/touchgame");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            //data += "&" + URLEncoder.encode("height", "UTF-8") + "=" + URLEncoder.encode(height, "UTF-8");
            //String data = URLEncoder.encode("{id:" + id + ", height:" + height + "}", "UTF-8");
            //String data = "{id:" + id + ", height:" + height + "}";

            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("height", height);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(json.toString());
            writer.flush();
            writer.close();
            os.close();

            //conn.connect();
            int responseCode=conn.getResponseCode();

            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(String result){

        // Nothing to do

    }

}
