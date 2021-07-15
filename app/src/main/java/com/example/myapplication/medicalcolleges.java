package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class medicalcolleges extends AppCompatActivity {
    Toolbar tlb4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topmedical);

        tlb4=findViewById(R.id.cust_toolbar4);
        setSupportActionBar(tlb4);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //for adding back button in toolbar
    }
}
