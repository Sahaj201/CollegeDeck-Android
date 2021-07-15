package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

public class bottomsheetfeedback extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.feedback_bottomsheet,container,false);

        EditText response=v.findViewById(R.id.respo);
        Button sbmt=v.findViewById(R.id.fdbck_btn);
        RatingBar rt=v.findViewById(R.id.ratingbar);
        RadioButton c1=v.findViewById(R.id.bug);
        RadioButton c2=v.findViewById(R.id.sug);
        RadioButton c3=v.findViewById(R.id.oth);

        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = String.valueOf(rt.getRating());  //storing rating value
                Boolean a1=c1.isChecked();
                Boolean a2=c2.isChecked();
                Boolean a3=c3.isChecked();
                if((a1==false && a2==false && a3==false))
                {
                    Toast.makeText(getActivity(), "Please fill all the details!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //store rating , text and type in database now
                    Toast.makeText(getActivity(), "Feedback submitted!", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
        return v;
    }
}
