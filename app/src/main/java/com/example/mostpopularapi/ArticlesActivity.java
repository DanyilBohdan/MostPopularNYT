package com.example.mostpopularapi;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticlesActivity extends AppCompatActivity {

    private TextView articleText;
    private TextView conten;
    private TextView link;
    private Result obArticle;
    private DBHelper db;
    private FloatingActionButton fab;
    private ImageView one;
    List<MediaMetadatum> im;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        obArticle = Import.ob;
        im = obArticle.getMedia().get(0).getMediaMetadata();

        articleText = findViewById(R.id.articleText);
        conten = findViewById(R.id.contentTextArticle);
        link = findViewById(R.id.link);
        fab = findViewById(R.id.btn_favorites);
        one = findViewById(R.id.iv);

        articleText.setText(obArticle.getTitle());
        String content;
        content = "" +
                "keywords: " + obArticle.getAdxKeywords() +
                "\ncount type: " + obArticle.getCountType() +
                "\ncolumn: "  + obArticle.getColumn() +
                "\nsection: " + obArticle.getSection() +
                "\nbyline: " + obArticle.getByline() +
                "\nabstract: " + obArticle.getAbstract() +
                "\npublished date: " + obArticle.getPublishedDate() +
                "\nupdated: " + obArticle.getUpdated();
        conten.setText(content);

        Picasso.get().load(im.get(2).getUrl()).into(one);

        link.setText("Link: " + obArticle.getUrl());

        db = new DBHelper(this, 1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialogFavorites = new Dialog(v.getContext());
                dialogFavorites.setContentView(R.layout.dialog);

                Button btnYes = (Button) dialogFavorites.findViewById(R.id.btn_Yes);
                Button btnNo = (Button) dialogFavorites.findViewById(R.id.btn_No);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.insertArticle(obArticle.getId(),obArticle.getTitle(),obArticle.getAdxKeywords(), obArticle.getCountType(),
                                obArticle.getColumn(), obArticle.getSection(), obArticle.getByline(), obArticle.getAbstract(),
                                obArticle.getPublishedDate(), obArticle.getUpdated(), obArticle.getUrl(), im.get(2).getUrl());
                        dialogFavorites.dismiss();
                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogFavorites.dismiss();
                    }
                });
                dialogFavorites.show();
            }
        });

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
