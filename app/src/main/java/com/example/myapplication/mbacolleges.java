package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class mbacolleges extends AppCompatActivity {
    Toolbar tlb3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topmba);

        tlb3=findViewById(R.id.cust_toolbar3);
        setSupportActionBar(tlb3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //for adding back button in toolbar
    }
}
