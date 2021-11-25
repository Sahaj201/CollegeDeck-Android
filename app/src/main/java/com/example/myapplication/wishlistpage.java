package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class wishlistpage extends AppCompatActivity {
    private ArrayList<College> cwishlist;
    FirebaseFirestore fb4;
    RecyclerView rv;
    String s;
    Toolbar t;//take androidx toolbar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlistpage);
        fb4=FirebaseFirestore.getInstance();
        rv=findViewById(R.id.Recycler_view);
        // creating our new array list 
        cwishlist = new ArrayList<>();
        rv.setHasFixedSize(true);//recycle view setting
        rv.setLayoutManager(new LinearLayoutManager(this));
        Intent i=getIntent();
        s=i.getStringExtra("name");
        // adding our array list to our recycler view adapter class. 
        MyRecycleAdapter clgRVAdapter = new MyRecycleAdapter(this, cwishlist);
        // setting adapter to our recycler view. 
        rv.setAdapter(clgRVAdapter);

        t=findViewById(R.id.cust_toolbar8);
        setSupportActionBar(t);

        // below line is use to get the data from Firebase Firestore. 
        // previously we were saving data on a reference of Courses 
        // now we will be getting the data from the same reference.


        fb4.collection(s).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()  {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are
                            // hiding our progress bar and adding
                            // our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                College c = d.toObject(College.class);

                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                cwishlist.add(c);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifuDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            clgRVAdapter.notifyDataSetChanged();
                        }
                        else
                            {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(wishlistpage.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(wishlistpage.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}