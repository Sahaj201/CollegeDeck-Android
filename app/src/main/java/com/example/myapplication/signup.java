package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class signup extends homepage {
    FirebaseFirestore fb;
    FirebaseAuth fa;
    EditText name, email, phoneno, pass, passs;
    String nm;
    String emaill, pno, pss, cpss;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.et7);
        email = findViewById(R.id.et8);
        phoneno = findViewById(R.id.et9);
        pass = findViewById(R.id.et10);
        passs = findViewById(R.id.et11);
        btn2=findViewById(R.id.nutton5);
        fb = FirebaseFirestore.getInstance();
        fa=FirebaseAuth.getInstance();
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm = name.getText().toString();
                emaill = email.getText().toString();
                pno = phoneno.getText().toString();
                pss = pass.getText().toString();
                cpss = passs.getText().toString();
                if (nm.length() != 0 && emaill.length() != 0 && pno.length() != 0 && pss.length() != 0) {
                    if (pss.equals(cpss)) {
                        fa.createUserWithEmailAndPassword(emaill,pss).addOnCompleteListener(signup.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    addDataToFirestore(nm, emaill, pno, pss);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(signup.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(signup.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(signup.this, "Enter the details!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void addDataToFirestore(String nmm, String eml, String phone, String password) {
        UserDataR user = new UserDataR(nmm, eml, phone, password);
        fb.collection("Users").document(nm).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(signup.this, "Signed up successfully", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), homepage.class);
                startActivity(intent1);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(signup.this, "Fail to signed up \n" + e, Toast.LENGTH_SHORT).show();
            }
        });

    }
}