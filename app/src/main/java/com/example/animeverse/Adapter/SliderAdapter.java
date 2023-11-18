package com.example.animeverse.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animeverse.Model.SliderModel;
import com.example.animeverse.PlayerActivity;
import com.example.animeverse.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyViewHolder> {

    private Context context;
    public SliderAdapter(Context context){
        this.context = context;
    }

    private List<SliderModel> sliderModelList =new ArrayList<>();

    public void renewItems(List<SliderModel> dataModels){
        this.sliderModelList = dataModels;
        notifyDataSetChanged();
    }

    public void deleteItems(int position){
        this.sliderModelList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.MyViewHolder viewHolder, int position) {
        viewHolder.slider_title.setText(sliderModelList.get(position).getTtitle());

        Glide.with(viewHolder.itemView).load(sliderModelList.get(position).getTurl()).into(viewHolder.slider_image);

        viewHolder.slider_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trailer_video = new Intent(context, PlayerActivity.class);
                trailer_video.putExtra("vid",sliderModelList.get(position).getTvid());
                trailer_video.putExtra("title",sliderModelList.get(position).getTtitle());
                v.getContext().startActivity(trailer_video);
            }
        });



    }

    @Override
    public int getCount() {
        return sliderModelList.size();
    }

    public class MyViewHolder extends ViewHolder {

        ImageView slider_image;
        TextView slider_title;
        public MyViewHolder(View itemView) {
            super(itemView);
            slider_image = itemView.findViewById(R.id.slider_thumbnail);
            slider_title = itemView.findViewById(R.id.slider_title);
        }
    }
}
