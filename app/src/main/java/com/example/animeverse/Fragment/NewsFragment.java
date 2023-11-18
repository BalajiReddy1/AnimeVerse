package com.example.animeverse.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animeverse.Adapter.NewsAdapter;
import com.example.animeverse.Model.NewsModel;
import com.example.animeverse.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class NewsFragment extends Fragment {

    private RecyclerView newsrecycler;
    NewsAdapter newsAdapter;
    public NewsFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_news, container, false);

        newsrecycler = (RecyclerView)view.findViewById(R.id.news_recycler);
        newsrecycler.setLayoutManager(new LinearLayoutManager(requireContext()));

        FirebaseRecyclerOptions<NewsModel> options =
                new FirebaseRecyclerOptions.Builder<NewsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("news"), NewsModel.class)
                        .build();

        newsAdapter = new NewsAdapter(options);
        newsrecycler.setAdapter(newsAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        newsAdapter.startListening();

    }
    @Override
    public void onStop() {
        super.onStop();
        newsAdapter.stopListening();

    }
}