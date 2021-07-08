package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

public class college_java extends AppCompatActivity {
    FirebaseFirestore ffb3;
    TextView t1,t2,t3;
    ImageView img;
    Toolbar tlc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.college_view);

        ffb3=FirebaseFirestore.getInstance();
        t1=findViewById(R.id.heading);
        t2=findViewById(R.id.description);
        t3=findViewById(R.id.url);
        img=findViewById(R.id.clg_img);
        tlc=findViewById(R.id.cust_collg_toolbar);

        setSupportActionBar(tlc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //for adding back button in toolbar


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
                    String desc=cd.getDescription();
                    String ur=cd.getUrl();
                    String colim=cd.getImage();    //we have stored image downloadable token as field in databse and we retrieve that token and pass it to glide and display images.

                    t1.setText(titl);
                    t2.setText(desc);
                    t3.setText(ur);
                    Glide.with(getApplicationContext()).load(colim).into(img);    //glide is a librar7y for dealing with images.

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
