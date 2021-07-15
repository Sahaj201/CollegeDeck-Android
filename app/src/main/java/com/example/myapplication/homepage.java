package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jetbrains.annotations.NotNull;

public class homepage extends AppCompatActivity {
    FirebaseFirestore fb1;
    Button btn1;
    TextView txt1,fb;
    EditText usnm,ps;
    String em,pass;
    ProgressBar ppbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fb1=FirebaseFirestore.getInstance();
        btn1 = findViewById(R.id.button);
        txt1 = findViewById(R.id.textView5);
        usnm=findViewById(R.id.editTextTextPersonName);
        ps=findViewById(R.id.editTextTextPassword);
        fb=findViewById(R.id.fgt_psd);
        ppbb=findViewById(R.id.login_pgbar);


        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(homepage.this,signup.class);
                startActivity(intent2);
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em=usnm.getText().toString();
                pass=ps.getText().toString();
                ppbb.setVisibility(View.VISIBLE);
                btn1.setVisibility(View.INVISIBLE);
                fb1.collection("Users").document(em).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                        UserDataR user=documentSnapshot.toObject(UserDataR.class);
                        String fn=user.getName();
                        String fp=user.getPassword();
                        if(fn.equals(em) && fp.equals(pass))
                        {
                            Toast.makeText(homepage.this, "Login successfully", Toast.LENGTH_SHORT).show();
                            Intent intent3=new Intent(getApplicationContext(),ui_back.class);
                            intent3.putExtra("name",fn);
                            startActivity(intent3);
                            finish();
                        }
                        else
                        {
                            ppbb.setVisibility(View.GONE);
                            btn1.setVisibility(View.VISIBLE);
                            Toast.makeText(homepage.this, "Wrong details", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        ppbb.setVisibility(View.GONE);
                        btn1.setVisibility(View.VISIBLE);
                        Toast.makeText(homepage.this, "No such account exists!", Toast.LENGTH_SHORT).show();
                    }}
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        ppbb.setVisibility(View.GONE);
                        btn1.setVisibility(View.VISIBLE);
                        Toast.makeText(homepage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ee=usnm.getText().toString();
                if(ee.isEmpty())
                {
                    Toast.makeText(homepage.this, "Please enter username!", Toast.LENGTH_SHORT).show();
                }
                else {
                    fb1.collection("Users").document(ee).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                UserDataR us = documentSnapshot.toObject(UserDataR.class);
                                String phn = us.getPhone();
                                Intent abc = new Intent(getApplicationContext(), entermobile.class);
                                abc.putExtra("phone", phn);
                                abc.putExtra("name",ee);
                                startActivity(abc);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(homepage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}