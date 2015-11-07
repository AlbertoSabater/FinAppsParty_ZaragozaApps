package com.example.zaragozaapps.matchpaisgame;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zaragozaapps.zaragozaapps.R;

import java.util.ArrayList;

/**
 * Created by sergiolazaromagdalena on 7/11/15.
 */
public class MatchPairsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] array = new Button[4][4];

    private Button buttonA1,buttonA2,buttonA3,buttonA4;
    private Button buttonB1,buttonB2,buttonB3,buttonB4;
    private Button buttonC1,buttonC2,buttonC3,buttonC4;
    private Button buttonD1,buttonD2,buttonD3,buttonD4;
    private TextView numtries;
    private int tries;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_pairs);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");

        settingButtons();
        setListener();

    }

    private void setListener(){
        buttonA1.setOnClickListener(this);
        buttonA2.setOnClickListener(this);
        buttonA3.setOnClickListener(this);
        buttonA4.setOnClickListener(this);

        buttonB1.setOnClickListener(this);
        buttonB2.setOnClickListener(this);
        buttonB3.setOnClickListener(this);
        buttonB4.setOnClickListener(this);

        buttonC1.setOnClickListener(this);
        buttonC2.setOnClickListener(this);
        buttonC3.setOnClickListener(this);
        buttonC4.setOnClickListener(this);

        buttonD1.setOnClickListener(this);
        buttonD2.setOnClickListener(this);
        buttonD3.setOnClickListener(this);
        buttonD4.setOnClickListener(this);
    }

    private void settingButtons(){
        //A
        buttonA1 = (Button) findViewById(R.id.buttonA1);
        buttonA2 = (Button) findViewById(R.id.buttonA2);
        buttonA3 = (Button) findViewById(R.id.buttonA3);
        buttonA4 = (Button) findViewById(R.id.buttonA4);

        //B
        buttonB1 = (Button) findViewById(R.id.buttonB1);
        buttonB2 = (Button) findViewById(R.id.buttonB2);
        buttonB3 = (Button) findViewById(R.id.buttonB3);
        buttonB4 = (Button) findViewById(R.id.buttonB4);

        //C
        buttonC1 = (Button) findViewById(R.id.buttonC1);
        buttonC2 = (Button) findViewById(R.id.buttonC2);
        buttonC3 = (Button) findViewById(R.id.buttonC3);
        buttonC4 = (Button) findViewById(R.id.buttonC4);

        //D
        buttonD1 = (Button) findViewById(R.id.buttonD1);
        buttonD2 = (Button) findViewById(R.id.buttonD2);
        buttonD3 = (Button) findViewById(R.id.buttonD3);
        buttonD4 = (Button) findViewById(R.id.buttonD4);
    }


    @Override
    public void onClick(View v) {
        Toast toast;
        switch(v.getId()){
            case R.id.buttonA1:
                new MatchPairsSendButton(this).execute(id,"2","2");
                break;
            case R.id.buttonA2:
                new MatchPairsSendButton(this).execute(id,"3","2");
                break;
            case R.id.buttonA3:
                new MatchPairsSendButton(this).execute(id,"4","2");
                break;
            case R.id.buttonA4:
                new MatchPairsSendButton(this).execute(id,"5","2");
                break;
            case R.id.buttonB1:
                new MatchPairsSendButton(this).execute(id,"2","3");
                break;
            case R.id.buttonB2:
                new MatchPairsSendButton(this).execute(id,"3","3");
                break;
            case R.id.buttonB3:
                new MatchPairsSendButton(this).execute(id,"4","3");
                break;
            case R.id.buttonB4:
                new MatchPairsSendButton(this).execute(id,"5","3");
                break;
            case R.id.buttonC1:
                new MatchPairsSendButton(this).execute(id,"2","4");
                break;
            case R.id.buttonC2:
                new MatchPairsSendButton(this).execute(id,"3","4");
                break;
            case R.id.buttonC3:
                new MatchPairsSendButton(this).execute(id,"4","4");
                break;
            case R.id.buttonC4:
                new MatchPairsSendButton(this).execute(id,"5","4");
                break;
            case R.id.buttonD1:
                new MatchPairsSendButton(this).execute(id,"2","5");
                break;
            case R.id.buttonD2:
                new MatchPairsSendButton(this).execute(id,"3","5");
                break;
            case R.id.buttonD3:
                new MatchPairsSendButton(this).execute(id,"4","5");
                break;
            case R.id.buttonD4:
                new MatchPairsSendButton(this).execute(id,"5","5");
                break;
        }

    }
}
