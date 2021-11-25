package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.SearchView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ui_back extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView tv1, usrhd1,gthp;
    Button bt1, bt2, bt3, bt4, fdbc,comp;
    DrawerLayout dl;
    NavigationView ngv;
    Toolbar tlb;
    SearchView scv;
    ListView lcv;
    ListViewAdapter lv;
    String nm1;
    FirebaseFirestore fd;
    RatingBar rt;
    List<String> list;
    String[] abc;
    ArrayList<CollegeNames> arrayList=new ArrayList<CollegeNames>();
    ArrayList<String> help=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui);
        //tv1=(TextView) findViewById(R.id.usr_1);
        bt1 = (Button) findViewById(R.id.bot1);
        bt2 = (Button) findViewById(R.id.bot2);
        bt3 = (Button) findViewById(R.id.bot3);
        bt4 = (Button) findViewById(R.id.bot4);
        fdbc = (Button) findViewById(R.id.fdbck_btn);
        comp=findViewById(R.id.bot69);
        lcv=findViewById(R.id.listsug);
        gthp=findViewById(R.id.get_help);

        /*this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View view=getSupportActionBar().getCustomView();
        //setting of custom actionbar

        ab=getSupportActionBar(); //object of ACtionbar
        ColorDrawable cd=new ColorDrawable(Color.parseColor("#F07D84"));
        ab.setBackgroundDrawable(cd);*/
        //color of custom action bar

        fd=FirebaseFirestore.getInstance();
        list=new ArrayList<>();

        abc=new String[]{"IIT Bombay","IIT Delhi","IIT Guwahati","IIT Hyderabad","IIT Indore","IIT Kanpur","IIT Kharagpur","IIT Kharagpur Law","IIT Madras","IIT Roorkee","Lovely Professional University","NIT Trichy","NLSIU Bangalore","NLU Delhi","NLU Hyderabad"};


        /*fd.collection("Colleges").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document : task.getResult())
                    {
                        list.add(document.getId());
                    }
                    Log.d("success",list.toString());
                }
                else
                {
                    Log.d("fail inn adding doc", String.valueOf(task.getException()));
                }
            }
        }); */   //this whole segment is used to extract all the name of colleges which is stored as document in a list

        for (int i = 0; i < abc.length; i++) {
            CollegeNames clgnm = new CollegeNames(abc[i]);
            // Binds all list element into an array
            arrayList.add(clgnm);
        }
        lv=new ListViewAdapter(this,arrayList);     //pass result to ListViewAdapter class
        lcv.setAdapter(lv);   //binds adapter to list view

        for(int j=0;j< abc.length;j++)
        {
            help.add(abc[j]);
        }  //to add abc to arraylist so that it can be passed to get help class for display of college names


        dl = findViewById(R.id.cust_drawer_layout);
        ngv = findViewById(R.id.cust_nav_bar);
        tlb = findViewById(R.id.cust_toolbar);
        scv = findViewById(R.id.search_cllg);

        setSupportActionBar(tlb);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tlb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        dl.addDrawerListener(toggle);
        toggle.syncState();   //navigation bar activities

        ngv.bringToFront();  //to make nav item clickable
        ngv.setItemIconTintList(null);   //to display menu icon with their original colors

        ngv.setNavigationItemSelectedListener(this);    //making navigation bar item clickable
        ngv.setCheckedItem(R.id.custom_nav_home);  //sets home icon as default selected in drawer

        Intent intent3 = getIntent();
        nm1 = intent3.getStringExtra("name");
        //tv1.setText(nm1);


        NavigationView navigationView = (NavigationView) findViewById(R.id.cust_nav_bar);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        usrhd1 = (TextView) header.findViewById(R.id.usrnm);
        //email = (TextView)header.findViewById(R.id.email);
        usrhd1.setText(nm1);
        //email.setText(personEmail);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ui_back.this, engineeringcolleges.class);
                startActivity(a);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(ui_back.this, mbacolleges.class);
                startActivity(b);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(ui_back.this, lawcolleges.class);
                startActivity(c);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(ui_back.this, medicalcolleges.class);
                startActivity(d);
            }
        });

        gthp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ioj=new Intent(ui_back.this,get_help.class);
                ioj.putExtra("college_list",help);
                startActivity(ioj);
            }
        });


        scv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent ij = new Intent(getApplicationContext(), college_java.class);
                ij.putExtra("college", query);
                ij.putExtra("name",nm1);
                startActivity(ij);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String txt=newText;
                lv.filter(txt);
                if(newText.length()!=0)
                {
                    lcv.setVisibility(View.VISIBLE);
                }
                else
                {
                    lcv.setVisibility(View.GONE);
                }
                return false;   //whenever text changes , this func will be called
            }
        });
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nn=new Intent(ui_back.this,compare_college_java.class);
                startActivity(nn);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START))     //if drawer is open then on back press it should only close
        {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.cust_talk:

                bottomsheetfeedback bottomsheet = new bottomsheetfeedback();
                bottomsheet.show(getSupportFragmentManager(), "Feedback Form");
                break;

            case R.id.custom_navprof:
                Intent i2 = new Intent(ui_back.this, editprofile.class);
                i2.putExtra("name", nm1);
                startActivity(i2);
                break;

            case R.id.custom_navlogout:
                logout();
                break;

            case R.id.cust_abtus:
                dialog();
                break;

            case R.id.wishlist:
                Intent i3=new Intent(getApplicationContext(),wishlistpage.class);
                i3.putExtra("name",nm1);
                startActivity(i3);
                break;

        }


        dl.closeDrawer(GravityCompat.START);   //this will close the drawer after we select any option from drawer
        return true;     //on clicking items in menu , it should return true
    }

    private void dialog() {
        final AlertDialog.Builder bld=new AlertDialog.Builder(ui_back.this);
        View mView = getLayoutInflater().inflate(R.layout.aboutus_dialog,null);
        bld.setView(mView);
        final AlertDialog ad= bld.create();
        //Button bb=ad.findViewById(R.id.abtsbt_but);
        ad.setCanceledOnTouchOutside(true);
        /*bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
            }
        });*/
        ad.show();
    }

    private void logout() {
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(ui_back.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to logout ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                // When the user click yes button
                                // then app will close
                                Toast.makeText(ui_back.this, "Successful Logout", Toast.LENGTH_SHORT).show();
                                Intent i3 = new Intent(getApplicationContext(), homepage.class);
                                startActivity(i3);
                                finish();

                            }
                        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

}

