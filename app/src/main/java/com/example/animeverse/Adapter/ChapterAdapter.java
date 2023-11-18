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
import com.example.animeverse.Model.ChapterModel;
import com.example.animeverse.Model.EpisodeModel;
import com.example.animeverse.PdfViewerActivity;
import com.example.animeverse.PlayerActivity;
import com.example.animeverse.R;
import com.example.animeverse.ViewingPdfActivity;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyViewHolder> {

    private List<ChapterModel> chapterModels;

    public ChapterAdapter(List<ChapterModel> chapterModels) {
        this.chapterModels = chapterModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.chap_number.setText(chapterModels.get(position).getPart());
        Glide.with(holder.itemView).load(chapterModels.get(position).getUrl()).into(holder.chap_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewingPdfActivity.class);
                intent.putExtra("part",chapterModels.get(position).getPart());
                intent.putExtra("pdfUrl",chapterModels.get(position).getPdfUrl());
                holder.itemView.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return chapterModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView chap_image;
        TextView chap_number;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            chap_image = itemView.findViewById(R.id.chp_image);
            chap_number = itemView.findViewById(R.id.chp_no);


        }
    }
}
