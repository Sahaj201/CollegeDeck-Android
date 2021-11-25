package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    Context mcontext;
    LayoutInflater lif;
    private List<CollegeNames> cgl=null;
    private ArrayList<CollegeNames> cag;

    public ListViewAdapter(Context cc, ArrayList<CollegeNames> arrayList) {
        mcontext=cc;
        cgl=arrayList;
        lif=LayoutInflater.from(mcontext);
        cag=new ArrayList<CollegeNames>();
        cag.addAll(arrayList);
    }

    public class ViewHolder{
        TextView name;
    }

    @Override
    public int getCount() {
        return cgl.size();
    }

    @Override
    public Object getItem(int position) {
        return cgl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null)
        {
            holder=new ViewHolder();
            convertView=lif.inflate(R.layout.list_view_items,null);
            // Locate the TextViews in listview_item.xml
            holder.name=(TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder) convertView.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(cgl.get(position).getCollegename());
        return convertView;
    }

    //filter class
    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        //Toast.makeText(mcontext, "inside filter", Toast.LENGTH_SHORT).show();
        cgl.clear();
        if(charText.length()==0)
        {
            cgl.addAll(cag);
        }
        else
        {
            //Toast.makeText(mcontext, "i2", Toast.LENGTH_SHORT).show();
            for(CollegeNames cn : cag)
            {
                if(cn.getCollegename().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    cgl.add(cn);
                }
            }
        }
        notifyDataSetChanged();
    }
}
