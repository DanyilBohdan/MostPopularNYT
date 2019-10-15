package com.example.mostpopularapi;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FavoriteArticle extends AppCompatActivity {

    private TextView articleText;
    private TextView conten;
    private TextView link;
    private long id;
    private DBHelper db;
    private Cursor cursor;
    private FloatingActionButton fab;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        fab = findViewById(R.id.btn_favorites);
        fab.hide();

        db = new DBHelper(this, 1);

        id = Import.id;
        articleText = findViewById(R.id.articleText);
        conten = findViewById(R.id.contentTextArticle);
        link = findViewById(R.id.link);
        imageView = findViewById(R.id.iv);

        cursor = db.viewArticleId(id);

        cursor.moveToFirst();

        articleText.setText(cursor.getString(cursor.getColumnIndex("title")));
        String content;
        content = "" +
                "keywords: " + cursor.getString(cursor.getColumnIndex("keywords")) +
                "\ncount type: " + cursor.getString(cursor.getColumnIndex("count_type")) +
                "\ncolumn: "  + cursor.getString(cursor.getColumnIndex("colum")) +
                "\nsection: " + cursor.getString(cursor.getColumnIndex("section")) +
                "\nbyline: " + cursor.getString(cursor.getColumnIndex("byline")) +
                "\nabstract: " + cursor.getString(cursor.getColumnIndex("abstract")) +
                "\npublished date: " + cursor.getString(cursor.getColumnIndex("published_date")) +
                "\nupdated: " + cursor.getString(cursor.getColumnIndex("updated"));
        conten.setText(content);

        Picasso.get().load(cursor.getString(cursor.getColumnIndex("url_image"))).into(imageView);

        link.setText("Link: " + cursor.getString(cursor.getColumnIndex("url")));

        Toolbar toolbar = findViewById(R.id.toolbarArticle);
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
