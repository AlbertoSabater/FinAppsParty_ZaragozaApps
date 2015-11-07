package com.example.zaragozaapps.zaragozaapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by alber on 07/11/2015.
 */
public class SetUserName extends AppCompatActivity{

    private EditText etUserName;
    private Button btConfirmUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_name);

        etUserName = (EditText) findViewById(R.id.etUserName);
        btConfirmUserName = (Button) findViewById(R.id.btConfirmUserName);

        btConfirmUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs =
                        getSharedPreferences("Preferences",Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("userName", String.valueOf(etUserName.getText()));
                editor.commit();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

}
