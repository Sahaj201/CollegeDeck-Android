package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class compare_college_java extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] options={"Description","Fees","Cutoff","Placement","How to apply"};
    Spinner spin;
    TextView h1,h2,d1,d2;
    EditText ec1,ec2;
    Button bn1;
    FirebaseFirestore f1;
    String a,b;
    Toolbar tt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_college);
        spin=findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);
        h1=findViewById(R.id.clghead1);
        h2=findViewById(R.id.clghead2);
        d1=findViewById(R.id.clgdet1);
        d2=findViewById(R.id.clgdet2);
        ec1=findViewById(R.id.cg1);
        ec2=findViewById(R.id.cg2);
        bn1=findViewById(R.id.compbtn);
        f1=FirebaseFirestore.getInstance();

        tt1=findViewById(R.id.cust_toolbarcomp);
        setSupportActionBar(tt1);             //adding toolbar



        ArrayAdapter ad=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,options);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(this, options[position], Toast.LENGTH_SHORT).show();


        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opt=options[position];


                h1.setText(ec1.getText().toString());
                h2.setText(ec2.getText().toString());

                //college 1
                f1.collection("Colleges").document(ec1.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            CollegeData cod=documentSnapshot.toObject(CollegeData.class);
                            if(opt=="Description")
                            {
                                a=cod.getDescription();
                            }
                            else if(opt=="Fees")
                            {
                                a=cod.getFees();
                            }
                            else if(opt=="Cutoff")
                            {
                                a=cod.getCutoff();
                            }
                            else if(opt=="Placement")
                            {
                                a=cod.getPlacement();
                            }
                            else if(opt=="How to apply")
                            {
                                a=cod.getHow_to_apply();
                            }

                            a=a.replace("_n","\n");
                            d1.setText(a);

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(compare_college_java.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



                //college 2
                f1.collection("Colleges").document(ec2.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            CollegeData cod=documentSnapshot.toObject(CollegeData.class);
                            if(opt=="Description")
                            {
                                b=cod.getDescription();
                            }
                            else if(opt=="Fees")
                            {
                                b=cod.getFees();
                            }
                            else if(opt=="Cutoff")
                            {
                                b=cod.getCutoff();
                            }
                            else if(opt=="Placement")
                            {
                                b=cod.getPlacement();
                            }
                            else if(opt=="How to apply")
                            {
                                b=cod.getHow_to_apply();
                            }

                            b=b.replace("_n","\n");
                            d2.setText(b);

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(compare_college_java.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
