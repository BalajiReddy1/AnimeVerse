package com.example.animeverse.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeverse.AnimeDetailActivity;
import com.example.animeverse.Model.ShowsModel;
import com.example.animeverse.R;

import java.util.List;

public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.MyViewHolder> {

    private List<ShowsModel> modelList;

    public ShowsAdapter(List<ShowsModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowsAdapter.MyViewHolder holder, int position) {

        holder.title.setText(modelList.get(position).getStitle());
        Glide.with(holder.imageView.getContext()).load(modelList.get(position).getSthumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendDataToDetailsActivity = new Intent(holder.itemView.getContext(), AnimeDetailActivity.class);
                sendDataToDetailsActivity.putExtra("title",modelList.get(position).getStitle());
                sendDataToDetailsActivity.putExtra("link",modelList.get(position).getSlink());
                sendDataToDetailsActivity.putExtra("cover",modelList.get(position).getScover());
                sendDataToDetailsActivity.putExtra("thumb",modelList.get(position).getSthumb());
                sendDataToDetailsActivity.putExtra("desc",modelList.get(position).getSdesc());
                sendDataToDetailsActivity.putExtra("cast",modelList.get(position).getScast());
                sendDataToDetailsActivity.putExtra("t_link",modelList.get(position).getTlink());

                ActivityOptionsCompat activityOptions = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity)holder.itemView.getContext(),holder.imageView,"imageMain");

                holder.itemView.getContext().startActivity(sendDataToDetailsActivity,activityOptions.toBundle());



            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
