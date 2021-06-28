package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class homepage extends AppCompatActivity {
    ViewPager mvp;
    int[] images={R.drawable.slia,R.drawable.slib,R.drawable.slic,R.drawable.slid,R.drawable.slie};
    Button btn1;
    TextView txt1;
    ViewPageAdapter viewpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mvp=(ViewPager)findViewById(R.id.view_pager_main);
        viewpa=new ViewPageAdapter(homepage.this,images);
        mvp.setAdapter(viewpa);;
        btn1=findViewById(R.id.button);
        txt1=findViewById(R.id.textView5);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homepage.this, "yes", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getApplicationContext(),signup.class);
                startActivity(intent1);
            }
        });

    }
}