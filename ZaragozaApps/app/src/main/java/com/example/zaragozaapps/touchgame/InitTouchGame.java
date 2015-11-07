package com.example.zaragozaapps.touchgame;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.zaragozaapps.zaragozaapps.R;

/**
 * Created by alber on 06/11/2015.
 */
public class InitTouchGame extends AppCompatActivity {

    /**
     * Shows the screen with one button for each future player
     */

    private Button btPlayer1;
    private Button btPlayer2;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inittouchgame);

        context = getApplicationContext();

        btPlayer1 = (Button) findViewById(R.id.btPlayer1);
        btPlayer2 = (Button) findViewById(R.id.btPlayer2);

        btPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(InitTouchGame.this);
                dialog.setMessage("Waiting for players...");
                dialog.show();

                new BackGroundInitTouchGame(context).execute("0");
            }
        });

        btPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(InitTouchGame.this);
                dialog.setMessage("Waiting for players...");
                dialog.show();

                new BackGroundInitTouchGame(context).execute("1");
            }
        });


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
