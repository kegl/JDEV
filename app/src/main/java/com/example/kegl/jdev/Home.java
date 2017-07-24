package com.example.kegl.jdev;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Home extends Activity {

    ListView list;
    ArrayList<People> data;
    PeopleAdapter pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.list = (ListView) findViewById(R.id.listviewPeople);
        this.data = new ArrayList<People>();
        this.pa = new PeopleAdapter(this, R.layout.people_item, this.data);
        this.list.setAdapter(pa);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new DownloadPeople(this).execute();
    }

    // my callback
    public void populate(ArrayList<People> result) {
        this.pa.clear();
        this.pa.addAll(result);
        this.pa.notifyDataSetChanged();
    }
}
