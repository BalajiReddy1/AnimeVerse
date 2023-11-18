package com.example.animeverse.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.animeverse.Adapter.MangaAdapter;
import com.example.animeverse.Adapter.MovieAdapter;
import com.example.animeverse.Adapter.ShowsAdapter;
import com.example.animeverse.Model.MangaModel;
import com.example.animeverse.Model.MovieModel;
import com.example.animeverse.Model.ShowsModel;
import com.example.animeverse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    private EditText searchEditText;
    private RecyclerView moviesSearchRecycler,showsSearchRecycler,comicSearchRecycler;
    private List<MangaModel> mangaModelList;
    private MangaAdapter mangaAdapter;

    private List<ShowsModel> showsModelList;
    private ShowsAdapter showsAdapter;

    private List<MovieModel> movieModelList;
    private MovieAdapter movieAdapter;

    private DatabaseReference databaseReference;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchEditText = view.findViewById(R.id.searchtext);
        moviesSearchRecycler = view.findViewById(R.id.moviessearchrecycler);
        showsSearchRecycler = view.findViewById(R.id.showssearchrecycler);
        comicSearchRecycler = view.findViewById(R.id.comicsearchrecycler);

       /* moviesSearchRecycler.setVisibility(View.GONE);
        comicSearchRecycler.setVisibility(View.GONE);
        showsSearchRecycler.setVisibility(View.GONE);

        */


        // Manga Search
        mangaModelList = new ArrayList<>();
        mangaAdapter = new MangaAdapter(mangaModelList);
        comicSearchRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        comicSearchRecycler.setAdapter(mangaAdapter);

        // Shows Search
        showsModelList = new ArrayList<>();
        showsAdapter = new ShowsAdapter(showsModelList);
        showsSearchRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        showsSearchRecycler.setAdapter(showsAdapter);

        // Movie Search
        movieModelList = new ArrayList<>();
        movieAdapter = new MovieAdapter(movieModelList);
        moviesSearchRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        moviesSearchRecycler.setAdapter(movieAdapter);


        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    // Clear the search results when the search text is empty
                    mangaModelList.clear();
                    mangaAdapter.notifyDataSetChanged();

                    movieModelList.clear();;
                    movieAdapter.notifyDataSetChanged();

                    showsModelList.clear();
                    showsAdapter.notifyDataSetChanged();

                } else {
                    performFirebaseSearch(s.toString());
                    performMovieSearch(s.toString());
                    performShowsSearch(s.toString());

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    private void performShowsSearch(String searchShows) {
        Query searchQuery = databaseReference
                .child("shows")
                .orderByChild("Stitle")
                .startAt(searchShows)
                .endAt(searchShows + "\uf8ff");
        searchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //moviesSearchRecycler.setVisibility(View.GONE);
               // comicSearchRecycler.setVisibility(View.GONE);

                // Show the relevant RecyclerView
                showsSearchRecycler.setVisibility(View.VISIBLE);


                showsModelList.clear();
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    ShowsModel showsModel = contentSnapShot.getValue(ShowsModel.class);
                    showsModelList.add(showsModel);
                }
                showsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Data retrieval failed: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void performMovieSearch(String searchMovies) {
        Query searchQuery = databaseReference
                .child("movies")
                .orderByChild("Ftitle")
                .startAt(searchMovies)
                .endAt(searchMovies + "\uf8ff");

        searchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               // comicSearchRecycler.setVisibility(View.GONE);
               // showsSearchRecycler.setVisibility(View.GONE);

                // Show the relevant RecyclerView
                moviesSearchRecycler.setVisibility(View.VISIBLE);


                movieModelList.clear();
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    MovieModel movieModel = contentSnapShot.getValue(MovieModel.class);
                    movieModelList.add(movieModel);
                }
                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Data retrieval failed: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    // Searching Manga From Firebase
    private void performFirebaseSearch(String searchText) {
        Query searchQuery = databaseReference
                .child("comic")
                .orderByChild("Ctitle")
                .startAt(searchText)
                .endAt(searchText + "\uf8ff");

        searchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //moviesSearchRecycler.setVisibility(View.GONE);
                //showsSearchRecycler.setVisibility(View.GONE);
                // Show the relevant RecyclerView
                comicSearchRecycler.setVisibility(View.VISIBLE);


                mangaModelList.clear();
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    MangaModel mangaModel = contentSnapShot.getValue(MangaModel.class);
                    mangaModelList.add(mangaModel);
                }
                mangaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Data retrieval failed: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}