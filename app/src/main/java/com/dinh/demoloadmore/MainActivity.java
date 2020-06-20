package com.dinh.demoloadmore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dinh.demoloadmore.adapter.SongAdapter;
import com.dinh.demoloadmore.adapter.SongNewAdapter;
import com.dinh.demoloadmore.model.Song;
import com.dinh.demoloadmore.service.APIService;
import com.dinh.demoloadmore.service.APIUntil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView,rc_category;
    ProgressBar progressBar;

    int size = 10;
    boolean isLoading = false;
    static boolean loadmore=true;

    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = APIUntil.getServer();

        recyclerView = findViewById(R.id.rc_home);
        progressBar = findViewById(R.id.progressBar);

        callAPI();
    }

    private void callAPI() {

        apiService.getProduct(0, size).enqueue(new Callback<ArrayList<Song>>() {
            @Override
            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {
                Log.e("hahaha", response.body().size() + "");
                recyclerView.setHasFixedSize(true);
                SongNewAdapter recyclerViewAd = new SongNewAdapter(response.body(), getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(recyclerViewAd);
            }

            @Override
            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {
                Log.e("HUHUHU", t.getMessage());
            }

        });
    }
}