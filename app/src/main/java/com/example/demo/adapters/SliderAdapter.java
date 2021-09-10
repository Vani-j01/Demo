package com.example.demo.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.R;
import com.example.demo.model.SlideshowDataModel;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    //Context and array
    private  Context context;
    private List<SlideshowDataModel> slideItems = new ArrayList<>();


    //constructor
    public  SliderAdapter(Context context, List<SlideshowDataModel> slideItems){
        this.context = context;
        this.slideItems= slideItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_slider_image, null);
        return new SliderAdapterVH(inflate) ;
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        SlideshowDataModel sliderItem = slideItems.get(position);
        Picasso.get().load(sliderItem.getImage()).into(viewHolder.imageView);

    }


    @Override
    public int getCount() {
        return slideItems.size();
    }
    class  SliderAdapterVH extends  SliderViewAdapter.ViewHolder{
        ImageView imageView;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sliderImage);
        }
    }
}


