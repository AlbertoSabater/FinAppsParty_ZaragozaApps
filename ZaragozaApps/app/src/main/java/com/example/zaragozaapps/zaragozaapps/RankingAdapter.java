package com.example.zaragozaapps.zaragozaapps;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alber on 07/11/2015.
 */
public class RankingAdapter extends ArrayAdapter<UserPoints> {
    private final Context context;
    private final ArrayList<UserPoints> values;

    public RankingAdapter(Context context, ArrayList<UserPoints> values) {
        super(context, R.layout.cellview, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cellview, parent, false);

        TextView tvUserName = (TextView) rowView.findViewById(R.id.tvUserName);
        TextView tvPoints = (TextView) rowView.findViewById(R.id.tvPoints);

        tvUserName.setText(values.get(position).getUser());
        tvPoints.setText(values.get(position).getPoints());

        return rowView;
    }

}