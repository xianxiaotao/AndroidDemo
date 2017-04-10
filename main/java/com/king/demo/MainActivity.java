package com.king.demo;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        // Create an array adapter for the list view, using the Ipsum headlines array
        ListView listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<>(this, layout, Items.names));
        setContentView(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Class<?> clazz = Class.forName(Items.activities[position]);
                    startActivity(new Intent(MainActivity.this, clazz));
                } catch (ClassNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Open " + Items.activities[position] +
                            " failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
