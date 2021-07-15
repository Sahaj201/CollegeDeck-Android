package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class editprofile extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    String em;
    FirebaseFirestore fb3;
    Button ab1;
    ImageButton b2,b3,b4,b5;
    int c,y1=1;
    Toolbar tlb3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        et1 = findViewById(R.id.ed1);
        et2 = findViewById(R.id.ed2);
        et3 = findViewById(R.id.ed3);
        et4 = findViewById(R.id.ed4);
        ab1=findViewById(R.id.btn_s9);
        b3=findViewById(R.id.i2);
        b4=findViewById(R.id.i3);
        b5=findViewById(R.id.i4);

        fb3 = FirebaseFirestore.getInstance();
        Intent in5=getIntent();
        em=in5.getStringExtra("name");

        tlb3=findViewById(R.id.cust_toolbar5);
        setSupportActionBar(tlb3);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //for adding back button in toolbar


        fb3.collection("Users").document(em).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserDataR user = documentSnapshot.toObject(UserDataR.class);
                String fn = user.getName();
                String fp = user.getPassword();
                String em = user.getEmail();
                String ph = user.getPhone();
                et1.setHint(fn);
                et1.setEnabled(false);
                et2.setHint(em);
                et2.setEnabled(false);
                et3.setHint(fp);
                et3.setEnabled(false);
                et4.setHint(ph);
                et4.setEnabled(false);
                ab1.setEnabled(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(editprofile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //for changing email
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=c+1;
                et2.setEnabled(true);
                ab1.setEnabled(true);
                ab1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String s=et2.getText().toString();
                            if (s.length() != 0) {
                                    et2.setHint(s);
                                    fb3.collection("Users").document(em).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            UserDataR user = documentSnapshot.toObject(UserDataR.class);
                                            user.setEmail(s);
                                            fb3.collection("Users").document(em).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(editprofile.this, "Email changed successfully", Toast.LENGTH_SHORT).show();
                                                    et2.setEnabled(false);
                                                    ab1.setEnabled(false);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull @NotNull Exception e) {
                                                    Toast.makeText(editprofile.this, "Problem in email changing", Toast.LENGTH_SHORT).show();
                                                    et2.setEnabled(false);
                                                    ab1.setEnabled(false);
                                                }
                                            });

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {
                                            Toast.makeText(editprofile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    Toast.makeText(editprofile.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                                    et2.setEnabled(false);
                                    ab1.setEnabled(false);
                                }
                            }

                    });
                }
        });

        //for changing password
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=c+1;
                et3.setEnabled(true);
                ab1.setEnabled(true);
                ab1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s=et3.getText().toString();
                        if (s.length() != 0) {
                            et3.setHint(s);
                            fb3.collection("Users").document(em).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    UserDataR user = documentSnapshot.toObject(UserDataR.class);
                                    user.setPassword(s);
                                    fb3.collection("Users").document(em).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(editprofile.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                            et3.setEnabled(false);
                                            ab1.setEnabled(false);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {
                                            Toast.makeText(editprofile.this, "Problem in password changing", Toast.LENGTH_SHORT).show();
                                            et3.setEnabled(false);
                                            ab1.setEnabled(false);
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    Toast.makeText(editprofile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(editprofile.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                            et3.setEnabled(false);
                            ab1.setEnabled(false);
                        }
                    }

                });
            }
        });


        //for changing phone no
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=c+1;
                et4.setEnabled(true);
                ab1.setEnabled(true);
                ab1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s=et4.getText().toString();
                        if (s.length() != 0) {
                            et4.setHint(s);
                            fb3.collection("Users").document(em).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    UserDataR user = documentSnapshot.toObject(UserDataR.class);
                                    user.setPhone(s);
                                    fb3.collection("Users").document(em).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(editprofile.this, "Phone No. changed successfully", Toast.LENGTH_SHORT).show();
                                            et4.setEnabled(false);
                                            ab1.setEnabled(false);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {
                                            Toast.makeText(editprofile.this, "Problem in Phone No. changing", Toast.LENGTH_SHORT).show();
                                            et4.setEnabled(false);
                                            ab1.setEnabled(false);
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    Toast.makeText(editprofile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(editprofile.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                            et4.setEnabled(false);
                            ab1.setEnabled(false);
                        }
                    }

                });
            }
        });
    }
}