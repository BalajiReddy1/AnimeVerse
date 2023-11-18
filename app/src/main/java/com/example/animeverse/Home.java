package com.example.animeverse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.animeverse.Fragment.AnimeFragment;
import com.example.animeverse.Fragment.ComicFragment;
import com.example.animeverse.Fragment.FavouriteFragment;
import com.example.animeverse.Fragment.NewsFragment;
import com.example.animeverse.Fragment.SearchFragment;
import com.example.animeverse.IntroUI.Signup;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AnimeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(selectedListener);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,new AnimeFragment());
        transaction.commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AnimeFragment()).commit();
        } else if (itemId == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            // After logging out, you can navigate the user back to the login screen or any other desired screen.
            Intent intent = new Intent(Home.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(Home.this, "Logout Successful!!!", Toast.LENGTH_SHORT).show();
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();

                    if(itemId == R.id.anime){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new AnimeFragment())
                                .commit();
                        return true;
                    } else if (itemId == R.id.comic) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new ComicFragment())
                                .commit();
                        return true;
                    } else if(itemId == R.id.news){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new NewsFragment())
                                .commit();
                        return true;
                    } else if(itemId == R.id.search){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new SearchFragment())
                                .commit();
                        return true;
                    }
                    return false;
                }
            };
}