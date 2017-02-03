package com.example.sabinmj.myapp;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Fragemnt_Add extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragemnt__add);

        Middle_Fragment frg1=new Middle_Fragment();//create the fragment instance for the middle fragment
        Bottom_Fragment frg2=new Bottom_Fragment();//create the fragment instance for the bottom fragment

        FragmentManager manager=getSupportFragmentManager();//create an instance of fragment manager

        FragmentTransaction transaction=manager.beginTransaction();//create an instance of Fragment-transaction

        transaction.add(R.id.f1, frg1, "Frag_Middle_tag");
        //transaction.add(R.id.f2,frg2 , "Frag_Bottom_tag");


        transaction.commit();

    }
    }
}
