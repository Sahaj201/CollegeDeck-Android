package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ViewHolder>
{
    private ArrayList<College> cwishlist;
    private Context context;

    AlertDialog.Builder builder;
    MyRecycleAdapter(Context context, ArrayList<College> cwishlist)
    {
        this.context=context;
        this.cwishlist=cwishlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.textview, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        College cl = cwishlist.get(position);
        holder.txtname.setText(cl.getClgn());
        //holder.imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        holder.ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder= new AlertDialog.Builder(context);
                builder.setCancelable(false);
                builder.setMessage("Selected College will be removed!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cwishlist.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0, cwishlist.size());
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(context.getApplicationContext(),"you choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Are you sure");
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cwishlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton ib;
        public TextView txtname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ib = (ImageButton) itemView.findViewById(R.id.del);
            this.txtname = (TextView) itemView.findViewById(R.id.txt12);

        }
    }
}