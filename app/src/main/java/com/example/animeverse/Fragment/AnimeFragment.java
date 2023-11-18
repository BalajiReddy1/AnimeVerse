package com.example.animeverse.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.animeverse.Adapter.MovieAdapter;
import com.example.animeverse.Adapter.ShowsAdapter;
import com.example.animeverse.Adapter.SliderAdapter;
import com.example.animeverse.Model.MovieModel;
import com.example.animeverse.Model.ShowsModel;
import com.example.animeverse.Model.SliderModel;
import com.example.animeverse.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class AnimeFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private List<MovieModel> movieModels;

    private List<ShowsModel> showsModels;
    private RecyclerView movieRecyclerView, showsRecyclerView;
    private MovieAdapter movieAdapter;
    private ShowsAdapter showsAdapter;

    private List<SliderModel> sliderModels;

    private SliderAdapter sliderAdapter;



    public AnimeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("AnimeFragment", "onCreateView() called");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anime, container, false);
        FirebaseApp.initializeApp(requireContext());

        SliderView sliderView = view.findViewById(R.id.sliderview);
        sliderAdapter = new SliderAdapter(requireContext());
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        renewItems(sliderView);

        DatabaseReference Fref = database.getReference("movies");
        movieRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        movieRecyclerView.setLayoutManager(layoutManager);

        movieModels = new ArrayList<>();
        movieAdapter = new MovieAdapter(movieModels);
        movieRecyclerView.setAdapter(movieAdapter);

        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot contentSnapShot:snapshot.getChildren()){
                    MovieModel dataModel = contentSnapShot.getValue(MovieModel.class);
                    movieModels.add(dataModel);
                }
                movieAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadShowsData(view);
        loadFirebaseForSlider(view);
        return view;
    }

    private void loadFirebaseForSlider(View view) {
        myRef.child("trailer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot contentSlider: snapshot.getChildren()){
                    SliderModel sliderItem = contentSlider.getValue(SliderModel.class);
                    sliderModels.add(sliderItem);
                }
                sliderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void renewItems(View view){

        sliderModels = new ArrayList<>();
        SliderModel dataItems = new SliderModel();
        sliderModels.add(dataItems);

        sliderAdapter.renewItems(sliderModels);
        sliderAdapter.deleteItems(0);
    }
    private void loadShowsData(View view) {

        DatabaseReference Sref = database.getReference("shows");
        showsRecyclerView = view.findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        showsRecyclerView.setLayoutManager(layoutManager);

        showsModels = new ArrayList<>();
        showsAdapter = new ShowsAdapter(showsModels);

        showsRecyclerView.setAdapter(showsAdapter);
        Sref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot contentSnapShot: snapshot.getChildren()){
                    ShowsModel newShowsModel = contentSnapShot.getValue(ShowsModel.class);
                    showsModels.add(newShowsModel);
                }
                showsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}