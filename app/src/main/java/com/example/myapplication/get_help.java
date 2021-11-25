package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class get_help extends AppCompatActivity {
    ListView lsv;
    ArrayList<String> nlist;
    Toolbar tlb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help);
        lsv=findViewById(R.id.list_gethelp);
        tlb1=findViewById(R.id.cust_toolbar_gethelp);

        setSupportActionBar(tlb1);

        Intent ioj=getIntent();
        nlist=ioj.getStringArrayListExtra("college_list");

        ArrayAdapter<String> ads=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nlist);
        lsv.setAdapter(ads);

        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) lsv.getItemAtPosition(position);
                Toast.makeText(get_help.this,clickedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}