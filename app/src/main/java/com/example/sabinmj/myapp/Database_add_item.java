package com.example.sabinmj.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sabinmj.myapp.util.sqlDbAdaptor;

public class Database_add_item extends AppCompatActivity {
    private EditText productname;
    private EditText productdiscription;
    private EditText productprice;
    private EditText productquantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_add_item);
        productname=(EditText) findViewById(R.id.etname);
        productdiscription=(EditText) findViewById(R.id.etdiscription);
        productprice=(EditText) findViewById(R.id.etprice);
        productquantity=(EditText) findViewById(R.id.etquantity);
    }

    public void additem_sql(View view) {
        String n=productname.getText().toString();
        String disc=productdiscription.getText().toString();
        String price=productprice.getText().toString();
        String quanti=productquantity.getText().toString();

        //persisting the data
        sqlDbAdaptor dbAdapter = new sqlDbAdaptor(getBaseContext());
        dbAdapter.open();

        dbAdapter.insertContact(n,disc,price,quanti);
        Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();
        Log.d("save", "");
        dbAdapter.close();



    }

    public void Show_item(View view) {
        Intent intent= new Intent(getBaseContext(),database_show_database.class);
        startActivity(intent);
    }
}
