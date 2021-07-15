package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class verifyotp extends AppCompatActivity {
    EditText bn1, bn2, bn3, bn4, bn5, bn6, nps1, nps2;
    TextView m1;
    Button sb1, pswsub;
    String getotpbackend, usrname;
    ProgressBar ppb2;
    FirebaseFirestore fbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);

        bn1 = findViewById(R.id.otp1);
        bn2 = findViewById(R.id.otp2);
        bn3 = findViewById(R.id.otp3);
        bn4 = findViewById(R.id.otp4);
        bn5 = findViewById(R.id.otp5);
        bn6 = findViewById(R.id.otp6);
        ppb2 = findViewById(R.id.verify_otp_pgbar);
        nps1 = findViewById(R.id.newps);
        nps2 = findViewById(R.id.cnfnewps);
        pswsub = findViewById(R.id.submps);

        fbn = FirebaseFirestore.getInstance();

        Intent x = getIntent();
        usrname = x.getStringExtra("name");

        m1 = findViewById(R.id.hid);
        m1.setText(String.format("+91-%s", getIntent().getStringExtra("phone")));
        getotpbackend = getIntent().getStringExtra("backotp");

        sb1 = findViewById(R.id.verf);

        sb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bn1.getText().toString().isEmpty() && !bn2.getText().toString().isEmpty() && !bn3.getText().toString().isEmpty() && !bn4.getText().toString().isEmpty() && !bn5.getText().toString().isEmpty() && !bn6.getText().toString().isEmpty()) {
                    String enteredcode = bn1.getText().toString() + bn2.getText().toString() + bn3.getText().toString() + bn4.getText().toString() + bn5.getText().toString() + bn6.getText().toString();
                    if (getotpbackend != null) {
                        ppb2.setVisibility(View.VISIBLE);
                        sb1.setVisibility(View.INVISIBLE);
                        nps1.setVisibility(View.VISIBLE);
                        nps2.setVisibility(View.VISIBLE);
                        pswsub.setVisibility(View.VISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getotpbackend, enteredcode);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                ppb2.setVisibility(View.INVISIBLE);
                                sb1.setVisibility(View.VISIBLE);

                                pswsub.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (nps1.getText().toString().equals(nps2.getText().toString())) {
                                            fbn.collection("Users").document(usrname).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    UserDataR ud = documentSnapshot.toObject(UserDataR.class);
                                                    ud.setPassword(nps1.getText().toString());
                                                    //Toast.makeText(verifyotp.this, "inside", Toast.LENGTH_SHORT).show();
                                                    fbn.collection("Users").document(usrname).set(ud).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(verifyotp.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                                            Intent y=new Intent(getApplicationContext(),homepage.class);
                                                            startActivity(y);
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull @NotNull Exception e) {
                                                            Toast.makeText(verifyotp.this, "Problem in password changing", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull @NotNull Exception e) {
                                                    Toast.makeText(verifyotp.this, "Failed to change password", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            //Toast.makeText(verifyotp.this, "OTP Verified", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(verifyotp.this, "Password doesn't match!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        ppb2.setVisibility(View.INVISIBLE);
                        sb1.setVisibility(View.VISIBLE);
                        Toast.makeText(verifyotp.this, "Please check internet connection!", Toast.LENGTH_SHORT).show();
                    }

                    //Toast.makeText(verifyotp.this, "OTP Verify", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(verifyotp.this, "Please enter all numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        otpmove();   //function for making every box move after entering number
    }

    private void otpmove() {
        bn1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    bn2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bn2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    bn3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bn3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    bn4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bn4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    bn5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bn5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    bn6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}