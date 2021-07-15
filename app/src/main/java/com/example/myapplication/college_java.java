package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

public class college_java extends AppCompatActivity {
    FirebaseFirestore ffb3;
    TextView t1;
    ImageButton det_btn,fees_btn,hwt_btn,url_btn,ctof_btn,plc_btn;
    ImageView img;
    Toolbar tlc;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.college_view);

        ffb3=FirebaseFirestore.getInstance();
        t1=findViewById(R.id.heading);
        //t3=findViewById(R.id.url);
        img=findViewById(R.id.clg_img);
        det_btn=findViewById(R.id.detailsbt);
        fees_btn=findViewById(R.id.feesbt);
        hwt_btn=findViewById(R.id.hwtbt);
        url_btn=findViewById(R.id.urll);
        ctof_btn=findViewById(R.id.ctofbt);
        plc_btn=findViewById(R.id.plcmbt);


        tlc=findViewById(R.id.cust_collg_toolbar);
        setSupportActionBar(tlc);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //for adding back button in toolbar


        Intent ij=getIntent();
        String clg=ij.getStringExtra("college");
        Toast.makeText(this, clg, Toast.LENGTH_SHORT).show();

        ffb3.collection("Colleges").document(clg).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    CollegeData cd=documentSnapshot.toObject(CollegeData.class);

                    String titl=cd.getTitle();
                    String ur=cd.getUrl();
                    String colim=cd.getImage();    //we have stored image downloadable token as field in databse and we retrieve that token and pass it to glide and display images.

                    t1.setText(titl);
                    Glide.with(getApplicationContext()).load(colim).into(img);    //glide is a librar7y for dealing with images.

                    det_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle cc=new Bundle();
                            cc.putString("clg",clg);
                            detailsbtootm dbt=new detailsbtootm();
                            dbt.setArguments(cc);
                            dbt.show(getSupportFragmentManager(),"details");
                        }
                    });
                    fees_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle cd=new Bundle();
                            cd.putString("clg",clg);
                            bottomsheet_fees bff=new bottomsheet_fees();
                            bff.setArguments(cd);
                            bff.show(getSupportFragmentManager(),"fees");
                        }
                    });
                    hwt_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle cf=new Bundle();
                            cf.putString("clg",clg);
                            how_to_apply_bottomsheet htab=new how_to_apply_bottomsheet();
                            htab.setArguments(cf);
                            htab.show(getSupportFragmentManager(),"procedure");
                        }
                    });
                    ctof_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle cg=new Bundle();
                            cg.putString("clg",clg);
                            cutoff_bottomsheet cub=new cutoff_bottomsheet();
                            cub.setArguments(cg);
                            cub.show(getSupportFragmentManager(),"cutoff");
                        }
                    });
                    url_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle ch=new Bundle();
                            ch.putString("clg",clg);
                            url_bottomsheet ubs=new url_bottomsheet();
                            ubs.setArguments(ch);
                            ubs.show(getSupportFragmentManager(),"cutoff");
                        }
                    });
                    plc_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle ci=new Bundle();
                            ci.putString("clg",clg);
                            placement_bottomsheet plb=new placement_bottomsheet();
                            plb.setArguments(ci);
                            plb.show(getSupportFragmentManager(),"cutoff");
                        }
                    });

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(college_java.this, "Sorry! we dont have this college.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
