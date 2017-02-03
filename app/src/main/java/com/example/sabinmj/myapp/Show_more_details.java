package com.example.sabinmj.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sabinmj.myapp.util.sqlDbAdaptor;

public class Show_more_details extends AppCompatActivity {
TextView textView;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more_details);
        Intent intent123=getIntent();
textView = (TextView) findViewById(R.id.tvitemshow);
        textView2 = (TextView) findViewById(R.id.textView2);

        textView.setText(intent123.getStringExtra("name"));
        textView.setVisibility(View.GONE);
        sqlDbAdaptor dbAdapter = new sqlDbAdaptor(getBaseContext());
        dbAdapter.open();
        Cursor c = dbAdapter.getContact(textView.getText().toString());
        if (c.moveToFirst())

            c.moveToFirst();
    String id   = c.getString(0);//to retrieve first item in the database
   String name = c.getString(1);
        String dis = c.getString(2);
        String details = c.getString(3);
        textView2.setText("id--"+id +"   name--"+name+"  discription--"+dis+" price--"+details);

           }
}
