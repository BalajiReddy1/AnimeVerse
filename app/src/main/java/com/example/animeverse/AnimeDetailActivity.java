package com.example.animeverse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animeverse.Adapter.CastAdapter;
import com.example.animeverse.Adapter.EpisodeAdapter;
import com.example.animeverse.Model.CastModel;
import com.example.animeverse.Model.EpisodeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.github.glailton.expandabletextview.ExpandableTextView;

public class AnimeDetailActivity extends AppCompatActivity {


    private List<EpisodeModel> episodeModels;
    private EpisodeAdapter episodeAdapter;
    private RecyclerView episode_recycler_view,cast_recycler_view;
    private ImageView cover;
    private TextView title,desc;
    private List<CastModel> castModels;
    private CastAdapter castAdapter;

    private String title_movie;
    private String des_movie;
    private String link_movie;
    private String cast_movie;
    private String cover_movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_detail);

        cover = findViewById(R.id.Anime_Image);
        title = findViewById(R.id.title_details);
        desc = findViewById(R.id.description);
        episode_recycler_view = findViewById(R.id.recycler_parts);
        cast_recycler_view = findViewById(R.id.recycler_cast);

        title_movie = getIntent().getStringExtra("title");
        des_movie = getIntent().getStringExtra("desc");
        link_movie = getIntent().getStringExtra("link");
        cover_movie = getIntent().getStringExtra("cover");
        cast_movie = getIntent().getStringExtra("cast");


        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title_movie);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Glide.with(this).load(cover_movie).into(cover);

        cover.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        title.setText(title_movie);
        desc.setText(des_movie);

        loadPart();
        loadCast();

    }

    private void loadCast() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference castRef = database.getReference();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        cast_recycler_view.setLayoutManager(layoutManager);

        castModels = new ArrayList<>();
        castAdapter = new CastAdapter(castModels);
        cast_recycler_view.setAdapter(castAdapter);


        castRef.child("cast").child(cast_movie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot content: snapshot.getChildren()){
                    CastModel castModel = content.getValue(CastModel.class);
                    castModels.add(castModel);
                }
                castAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void loadPart() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference partRef = database.getReference();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        episode_recycler_view.setLayoutManager(layoutManager);

        episodeModels = new ArrayList<>();
        episodeAdapter = new EpisodeAdapter(episodeModels);
        episode_recycler_view.setAdapter(episodeAdapter);


        partRef.child("link").child(link_movie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot content: snapshot.getChildren()){
                    EpisodeModel episodeModel = content.getValue(EpisodeModel.class);
                    episodeModels.add(episodeModel);
                }
                episodeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}