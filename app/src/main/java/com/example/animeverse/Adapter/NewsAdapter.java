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
import com.example.animeverse.Model.NewsModel;
import com.example.animeverse.NewsDetailActivity;
import com.example.animeverse.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NewsAdapter extends FirebaseRecyclerAdapter<NewsModel, NewsAdapter.myviewholder> {


    public NewsAdapter(@NonNull FirebaseRecyclerOptions<NewsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull NewsModel model) {
        holder.header.setText(model.getHeader());
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);

        holder.img1.setOnClickListener((view)->{

            Intent intent = new Intent(holder.img1.getContext(), NewsDetailActivity.class);
            intent.putExtra("linkvalue",model.getLink());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.img1.getContext().startActivity(intent);

        });
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.link.getContext(), NewsDetailActivity.class);
                intent.putExtra("linkvalue",model.getLink());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.link.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newspagedesign,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView header, link;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.newsimage);
            header = itemView.findViewById(R.id.header);
            link = itemView.findViewById(R.id.newslink);

        }
    }
}
