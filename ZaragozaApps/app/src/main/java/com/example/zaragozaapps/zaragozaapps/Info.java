package com.example.zaragozaapps.zaragozaapps;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sergiolazaromagdalena on 11/7/15.
 */
public class Info extends AppCompatActivity{

    private TextView license, licenseVersion, authors, alberto, hector, sergio, github,
        githubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);


        license = (TextView) findViewById(R.id.license);
        licenseVersion = (TextView) findViewById(R.id.licenseVersion);
        authors = (TextView) findViewById(R.id.authors);
        alberto = (TextView) findViewById(R.id.alberto);
        hector = (TextView) findViewById(R.id.hector);
        sergio = (TextView) findViewById(R.id.sergio);
        github = (TextView) findViewById(R.id.github);
        githubLink = (TextView) findViewById(R.id.gitHubLink);

    }
}
