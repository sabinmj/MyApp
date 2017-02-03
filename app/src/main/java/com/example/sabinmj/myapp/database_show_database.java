package com.example.sabinmj.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sabinmj.myapp.util.sqlDbAdaptor;

import java.util.ArrayList;
import java.util.List;

public class database_show_database extends AppCompatActivity {
    ListView lv;
    ArrayList<String> lvcontents = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_show_database);
        lv = (ListView) findViewById(R.id.lvitems);
        sqlDbAdaptor dbAdapter = new sqlDbAdaptor(getBaseContext());
        dbAdapter.open();

        Cursor cursor = dbAdapter.getAllContacts();
        List<String> List = new ArrayList<String>();


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                lvcontents.add(cursor.getString(1));
            } while (cursor.moveToNext());

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,lvcontents );
        lv.setAdapter(adapter);

//to retrieve the data for item select------>
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvData = (TextView) view;
                Toast.makeText(getBaseContext(), tvData.getText().toString() + " " + i, Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getBaseContext(),Show_more_details.class);
                intent.putExtra("name",tvData.getText().toString());

                startActivity(intent);
            }
        });


    }
}