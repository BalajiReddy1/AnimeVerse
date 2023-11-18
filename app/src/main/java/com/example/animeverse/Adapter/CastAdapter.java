package com.example.animeverse.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeverse.Model.CastModel;
import com.example.animeverse.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MyViewHolder>{

    private List<CastModel> castModels;

    public CastAdapter(List<CastModel> castModels) {
        this.castModels = castModels;
    }




    @NonNull
    @Override
    public CastAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.MyViewHolder holder, int position) {
        holder.cast_name.setText(castModels.get(position).getCname());
        Glide.with(holder.itemView).load(castModels.get(position).getCurl()).into(holder.cast_image);

    }

    @Override
    public int getItemCount() {
        return castModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView cast_image;
        TextView cast_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cast_image = itemView.findViewById(R.id.cast_image);
            cast_name = itemView.findViewById(R.id.cast_name);
        }
    }
}
