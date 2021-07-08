package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class ui_back extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView tv1;
    Button bt1,bt2,bt3,bt4;
    DrawerLayout dl;
    NavigationView ngv;
    Toolbar tlb;
    SearchView scv;
    String ab="com.example.myapplication";
    String cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui);
        //tv1=(TextView) findViewById(R.id.usr_1);
        bt1=(Button) findViewById(R.id.bot1);
        bt2=(Button) findViewById(R.id.bot2);
        bt3=(Button) findViewById(R.id.bot3);
        bt4=(Button) findViewById(R.id.bot4);
        /*this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View view=getSupportActionBar().getCustomView();
        //setting of custom actionbar

        ab=getSupportActionBar(); //object of ACtionbar
        ColorDrawable cd=new ColorDrawable(Color.parseColor("#F07D84"));
        ab.setBackgroundDrawable(cd);*/
        //color of custom action bar


        dl=findViewById(R.id.cust_drawer_layout);
        ngv=findViewById(R.id.cust_nav_bar);
        tlb=findViewById(R.id.cust_toolbar);
        scv=findViewById(R.id.search_cllg);

        setSupportActionBar(tlb);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,dl,tlb,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        dl.addDrawerListener(toggle);
        toggle.syncState();   //navigation bar activities

        ngv.bringToFront();  //to make nav item clickable
        ngv.setItemIconTintList(null);   //to display menu icon with their original colors

        ngv.setNavigationItemSelectedListener(this);    //making navigation bar item clickable
        ngv.setCheckedItem(R.id.custom_nav_home);  //sets home icon as default selected in drawer

        Intent intent3=getIntent();
        String nm1=intent3.getStringExtra("name");
        //tv1.setText(nm1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ui_back.this,engineeringcolleges.class);
                startActivity(a);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(ui_back.this,mbacolleges.class);
                startActivity(b);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c=new Intent(ui_back.this,lawcolleges.class);
                startActivity(c);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d=new Intent(ui_back.this,medicalcolleges.class);
                startActivity(d);
            }
        });


        scv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //try {
                    //cd=ab+"."+query;
                    //Class xy=Class.forName(cd);
                    Intent ij=new Intent(getApplicationContext(),college_java.class);
                    ij.putExtra("college",query);
                    startActivity(ij);
                //} catch (ClassNotFoundException e) {
                  //  e.printStackTrace();
                //}
                //Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;   //whenever text changes , this func will be called
            }
    });
    }

    @Override
    public void onBackPressed() {
        if(dl.isDrawerOpen(GravityCompat.START))     //if drawer is open then on back press it should only close
        {
            dl.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {


        dl.closeDrawer(GravityCompat.START);   //this will close the drawer after we select any option from drawer
        return true;     //on clicking items in menu , it should return true
    }


}

