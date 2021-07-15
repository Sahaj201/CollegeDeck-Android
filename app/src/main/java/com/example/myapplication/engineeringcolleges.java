package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class engineeringcolleges extends AppCompatActivity {
    Toolbar tlb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topengineering);

        tlb1=findViewById(R.id.cust_toolbar1);
        setSupportActionBar(tlb1);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //for adding back button in toolbar
        //tlb1.setTitle(R.string.engineer);
    }
}
