package com.example.sabinmj.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SQL_add_item(View view) {
        Intent intent= new Intent(getBaseContext(),Database_add_item.class);
        startActivity(intent);
    }

    public void fragment(View view) {
        Intent intent= new Intent(getBaseContext(),Fragemnt_Add.class);
        startActivity(intent);
    }
}
