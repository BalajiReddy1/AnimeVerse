package com.example.animeverse.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeverse.Model.EpisodeModel;
import com.example.animeverse.PlayerActivity;
import com.example.animeverse.R;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.MyViewHolder> {


    private List<EpisodeModel> episodeModels;

    public EpisodeAdapter(List<EpisodeModel> episodeModels) {
        this.episodeModels = episodeModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.MyViewHolder holder, int position) {
        holder.episode_name.setText(episodeModels.get(position).getPart());
        Glide.with(holder.itemView).load(episodeModels.get(position).getUrl()).into(holder.episode_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                intent.putExtra("title",episodeModels.get(position).getPart());
                intent.putExtra("vid",episodeModels.get(position).getVidUrl());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return episodeModels.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView episode_image;
        TextView episode_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            episode_image = itemView.findViewById(R.id.part_image);
            episode_name = itemView.findViewById(R.id.part_name);
        }
    }
}
