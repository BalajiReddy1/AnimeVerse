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
import com.example.animeverse.ComicDetailActivity;
import com.example.animeverse.Model.MangaModel;
import com.example.animeverse.R;

import java.util.ArrayList;
import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MyViewHolder> {

    public List<MangaModel> mangaModels;
    private List<MangaModel> filteredMangaModels;

    public MangaAdapter(List<MangaModel> mangaModels) {
        this.mangaModels = mangaModels;
        this.filteredMangaModels = new ArrayList<>(mangaModels);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manga_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(mangaModels.get(position).getCtitle());
        Glide.with(holder.itemView.getContext()).load(mangaModels.get(position).getCthumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendDataToDetailsActivity = new Intent(holder.itemView.getContext(), ComicDetailActivity.class);
                sendDataToDetailsActivity.putExtra("title",mangaModels.get(position).getCtitle());
                sendDataToDetailsActivity.putExtra("link",mangaModels.get(position).getClink());
                sendDataToDetailsActivity.putExtra("cover",mangaModels.get(position).getCcover());
                sendDataToDetailsActivity.putExtra("thumb",mangaModels.get(position).getCthumb());
                sendDataToDetailsActivity.putExtra("desc",mangaModels.get(position).getCdes());

                ActivityOptionsCompat activityOptions = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity)holder.itemView.getContext(),holder.imageView,"imageMain");

                holder.itemView.getContext().startActivity(sendDataToDetailsActivity,activityOptions.toBundle());


            }
        });


    }

    @Override
    public int getItemCount() {
        return mangaModels.size();
    }

/*
    public void filterList(List<MangaModel> filteredList) {
        filteredMangaModels.clear();
        filteredMangaModels.addAll(filteredList);
        notifyDataSetChanged();
    }

 */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.manga_image);
            textView = itemView.findViewById(R.id.manga_title);
        }
    }
}
