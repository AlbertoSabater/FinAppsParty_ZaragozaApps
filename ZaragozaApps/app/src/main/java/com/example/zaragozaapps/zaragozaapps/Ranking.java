package com.example.zaragozaapps.zaragozaapps;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zaragozaapps.touchgame.BackGroundInitTouchGame;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alber on 07/11/2015.
 */
public class Ranking extends AppCompatActivity {

    public static ArrayList<UserPoints> up;
    public static ListView listview;
    public static RankingAdapter adapter;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_listview);

        this.context = getApplicationContext();

        listview = (ListView) findViewById(R.id.lvRanking);
        /*String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }*/

        new GetRanking(getApplicationContext()).execute("0");
      //up = new ArrayList<UserPoints>();

      //RankingAdapter adapter = new RankingAdapter(this, up);
      //listview.setAdapter(adapter);

        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }

        });*/
    }

    public static void setArray(ArrayList<UserPoints> a) {
        up = a;
    }

    public static void fillData() {
        Log.e("RANKING", String.valueOf(up.size()));
        RankingAdapter adapter = new RankingAdapter(context, up);
        listview.setAdapter(adapter);
    }
}
