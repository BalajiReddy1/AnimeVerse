package com.example.animeverse.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animeverse.Adapter.MangaAdapter;
import com.example.animeverse.Adapter.MovieAdapter;
import com.example.animeverse.Model.MangaModel;
import com.example.animeverse.Model.MovieModel;
import com.example.animeverse.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComicFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private List<MangaModel> mangaModelList;

    private MangaAdapter mangaAdapter;

    private RecyclerView mangaRecycler;



    public ComicFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("ComicFragment", "onCreateView() called");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comic, container, false);
        FirebaseApp.initializeApp(requireContext());

        DatabaseReference Fref = database.getReference("comic");
        mangaRecycler = view.findViewById(R.id.mangarecycler);


        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mangaRecycler.setLayoutManager(layoutManager);

        mangaModelList = new ArrayList<>();
        mangaAdapter = new MangaAdapter(mangaModelList);
        mangaRecycler.setAdapter(mangaAdapter);

        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot contentSnapShot:snapshot.getChildren()){
                    MangaModel mangaModel = contentSnapShot.getValue(MangaModel.class);
                    mangaModelList.add(mangaModel);
                }
                mangaAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        
        return view;
    }
}