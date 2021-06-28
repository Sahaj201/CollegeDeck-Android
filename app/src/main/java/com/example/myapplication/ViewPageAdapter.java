package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class ViewPageAdapter extends PagerAdapter {
    Context context;
    int[] images;
    LayoutInflater mli;

    public ViewPageAdapter(Context context,int[] images)
    {
        this.context=context;
        this.images=images;
        mli=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==((LinearLayout)object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,final int position) {
        View iv=mli.inflate(R.layout.slider_layout,container,false);
        ImageView imw=(ImageView) iv.findViewById(R.id.sld_img1);
        imw.setImageResource(images[position]);
        Objects.requireNonNull(container).addView(iv);

        return iv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
