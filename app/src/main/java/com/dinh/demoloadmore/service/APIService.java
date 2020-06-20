package com.dinh.demoloadmore.service;


import com.dinh.demoloadmore.model.Song;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("song/songpage")
    Call<ArrayList<Song>> getProduct(@Query("page") int page, @Query("size") int size);
}
