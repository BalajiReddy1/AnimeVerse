package com.example.animeverse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animeverse.Adapter.ChapterAdapter;
import com.example.animeverse.Adapter.EpisodeAdapter;
import com.example.animeverse.Adapter.MangaAdapter;
import com.example.animeverse.Model.ChapterModel;
import com.example.animeverse.Model.EpisodeModel;
import com.example.animeverse.Model.MangaModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComicDetailActivity extends AppCompatActivity {

    private List<MangaModel> mangaModelList;

    private MangaAdapter mangaAdapter;

    private ChapterAdapter chapterAdapter;

    private List<ChapterModel> chapterModels;

    private RecyclerView manga_recycler;

    private ImageView cover;

    private TextView title,desc;

    private String manga_name, manga_desc,manga_url,manga_cover;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        cover = findViewById(R.id.cover);
        title = findViewById(R.id.manga_name);
        desc = findViewById(R.id.manga_desc);
        manga_recycler = findViewById(R.id.recycler_manga);

        manga_name = getIntent().getStringExtra("title");
        manga_desc = getIntent().getStringExtra("desc");
        manga_url = getIntent().getStringExtra("link");
        manga_cover = getIntent().getStringExtra("cover");

        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(manga_name);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this).load(manga_cover).into(cover);

        cover.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        title.setText(manga_name);
        desc.setText(manga_desc);



        //loadManga();
        loadChapters();
    }

    private void loadChapters() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference cRef = database.getReference("chapters").child(manga_url);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        manga_recycler.setLayoutManager(layoutManager);

        chapterModels = new ArrayList<>();
        chapterAdapter = new ChapterAdapter(chapterModels);
        manga_recycler.setAdapter(chapterAdapter);

        cRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chapterModels.clear();
                for(DataSnapshot content: snapshot.getChildren()){
                    ChapterModel chapterModel = content.getValue(ChapterModel.class);
                    chapterModels.add(chapterModel);
                }
                chapterAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    /*
    private void loadManga() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mangaRef = database.getReference();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        manga_recycler.setLayoutManager(layoutManager);

        mangaModelList = new ArrayList<>();
        mangaAdapter = new MangaAdapter(mangaModelList);
        manga_recycler.setAdapter(mangaAdapter);

        mangaRef.child("link").child(manga_url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot content: snapshot.getChildren()){
                    MangaModel mangaModel = content.getValue(MangaModel.class);
                    mangaModelList.add(mangaModel);
                }
                mangaAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

     */



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}