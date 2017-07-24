package com.example.kegl.jdev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kegl on 177/6/.
 */

public class PeopleAdapter extends ArrayAdapter<People> {

    Context ctx;
    int myLayout;
    List<People> data;
    LayoutInflater li;

    public PeopleAdapter(Context context, int resource, List<People> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.myLayout = resource;
        this.data = objects;
        this.li = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View tmp = convertView;

        if (tmp == null) {
            tmp = this.li.inflate(this.myLayout, null);
        }
        People current = this.data.get(position);
        TextView tvLastname = (TextView) tmp.findViewById(R.id.textViewLastname);
        TextView tvFirstname = (TextView) tmp.findViewById(R.id.textViewFirstname);
        tvLastname.setText((current.getLastname()));
        tvFirstname.setText((current.getFirstname()));

        return tmp;
    }
}
