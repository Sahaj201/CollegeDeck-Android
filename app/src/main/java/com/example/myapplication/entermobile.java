package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class entermobile extends AppCompatActivity {
    EditText t1;
    Button b1;
    ProgressBar ppb;
    String usnm,prevph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entermobile);

        t1=findViewById(R.id.mobno);
        b1=findViewById(R.id.getotp);
        ppb=findViewById(R.id.send_otp_pgbar);

        Intent abc=getIntent();
        usnm=abc.getStringExtra("name");
        prevph=abc.getStringExtra("phone");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!t1.getText().toString().trim().isEmpty())
                {
                    if(t1.getText().toString().trim().length()==10 && t1.getText().toString().equals(prevph))
                    {
                        String pphh="+91"+t1.getText().toString();
                        ppb.setVisibility(View.VISIBLE);  //on clicking button , it makes progress bar visible and button invisible
                        b1.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                pphh,
                                60,
                                TimeUnit.SECONDS,
                                entermobile.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NotNull PhoneAuthCredential phoneAuthCredential) {
                                        ppb.setVisibility(View.GONE);
                                        b1.setVisibility(View.INVISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NotNull FirebaseException e) {
                                        ppb.setVisibility(View.GONE);
                                        b1.setVisibility(View.INVISIBLE);
                                        Toast.makeText(entermobile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NotNull String backotp,@NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        ppb.setVisibility(View.GONE);
                                        b1.setVisibility(View.INVISIBLE);
                                        Intent x=new Intent(getApplicationContext(),verifyotp.class);
                                        x.putExtra("phone",t1.getText().toString());
                                        x.putExtra("name",usnm);  //user name passed
                                        x.putExtra("backotp",backotp);   //this is the sent otp, and we will check it with the entered otp for verification
                                        startActivity(x);           //after sending otp , we are redirecting to verification page
                                    }
                                }
                        );
                        /*Intent x=new Intent(getApplicationContext(),verifyotp.class);
                        x.putExtra("phone",t1.getText().toString());
                        startActivity(x);*/
                    }
                    else
                    {
                        Toast.makeText(entermobile.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(entermobile.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}