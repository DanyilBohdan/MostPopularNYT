package com.example.mostpopularapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mostpopularapi.retrofit.API;
import com.example.mostpopularapi.retrofit.RetroArticle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewedFragment extends Fragment {

    private RecyclerView viewedArticle;
    private ArticlesAdapter viewedAdapter;
    private List<Result> viewedList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_articles, container, false);

        if(InternetConnection.checkConnection(v.getContext())){

            API api = RetroArticle.getRetrofitIntance().create(API.class);

            Call<Example> call = api.getViewedJSON();

            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {

                    viewedList = response.body().getResults();

                    viewedArticle = v.findViewById(R.id.rv_articles);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
                    viewedArticle.setLayoutManager(layoutManager);

                    viewedAdapter = new ArticlesAdapter(v.getContext(), viewedList);
                    viewedArticle.setAdapter(viewedAdapter);
                }
                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(v.getContext(),"No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        return v;
    }
}
