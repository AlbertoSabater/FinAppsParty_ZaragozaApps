package com.example.zaragozaapps.touchgame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zaragozaapps.zaragozaapps.PHPtest;
import com.example.zaragozaapps.zaragozaapps.R;

/**
 * Created by alber on 06/11/2015.
 */
public class TouchGame extends Activity {

    private int touchs = 0;
    private int totalTouchs = 0;
    private int height = 0;
    private Context context;
    private String id;

    private static TextView tvTouchs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touchgame);

        context = getApplicationContext();

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");

        tvTouchs = (TextView) findViewById(R.id.tvTouchs);
        Log.e("TOUCH GAME", "started");
        //new BackgroundWinner(this).execute();

        LinearLayout screen = (LinearLayout) findViewById(R.id.screemTouch);
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalTouchs != 100) {
                    totalTouchs++;
                    touchs++;

                    if (touchs == 20) {    // Send to pi
                        Log.e("TOUCH GAME", "100 touchs");
                        height ++;
                        new SendTouchs(context).execute(id, String.valueOf(height));
                        touchs = 0;
                    }

                    tvTouchs.setText(String.valueOf(totalTouchs));
                    //Log.e("TOUCH GAME", "new touch" + totalTouchs);
                }
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
