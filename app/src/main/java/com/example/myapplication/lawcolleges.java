package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class lawcolleges extends AppCompatActivity {
    Toolbar tlb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toplaw);

        tlb2=findViewById(R.id.cust_toolbar2);
        setSupportActionBar(tlb2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //for adding back button in toolbar
    }
}
