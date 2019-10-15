package com.example.mostpopularapi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>{

    private Context parent;
    private List<Result> mArticles;
    private Result articles;

    public ArticlesAdapter(Context parent, List<Result> dataList){
        this.parent = parent;
        mArticles = dataList;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item, viewGroup, false);
        ArticlesViewHolder viewHolder = new ArticlesViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder articlesViewHolder, int position) {
        articles = mArticles.get(position);
        articlesViewHolder.listItemView.setText(articles.getTitle());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder {

        TextView listItemView;

        public ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemView = itemView.findViewById(R.id.text_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positionIndex = getAdapterPosition();
                    Result impID = mArticles.get(positionIndex);
                    Intent intent = new Intent(parent, ArticlesActivity.class);
                    Import.ob = impID;
                    parent.startActivity(intent);
                }
            });
        }
    }
}
