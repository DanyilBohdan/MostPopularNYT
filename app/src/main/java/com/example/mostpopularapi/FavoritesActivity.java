package com.example.mostpopularapi;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView favoritesArticle;
    private FavoritesAdapter favoritesAdapter;
    private Cursor cursor;
    private DBHelper db;
    private int countItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);

        db = new DBHelper(this, 1);

        cursor = db.viewArticle();
        countItems = db.viewKol();

        favoritesArticle = findViewById(R.id.rv_fav);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        favoritesArticle.setLayoutManager(layoutManager);

        favoritesAdapter = new FavoritesAdapter( countItems,this, cursor);
        favoritesArticle.setAdapter(favoritesAdapter);

        Toolbar toolbar = findViewById(R.id.toolbarFav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
