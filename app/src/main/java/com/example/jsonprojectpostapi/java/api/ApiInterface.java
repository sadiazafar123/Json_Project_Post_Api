package com.example.jsonprojectpostapi.java.api;

import com.example.jsonprojectpostapi.java.model.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("posts")
    Call<List<ApiResponse>> getPost();

}
