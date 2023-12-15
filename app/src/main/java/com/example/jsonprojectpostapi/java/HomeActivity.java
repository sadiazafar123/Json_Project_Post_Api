package com.example.jsonprojectpostapi.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.jsonprojectpostapi.R;
import com.example.jsonprojectpostapi.java.adapter.RecyclerAdapter;
import com.example.jsonprojectpostapi.java.api.ApiInterface;
import com.example.jsonprojectpostapi.java.api.RetrofitInstance;
import com.example.jsonprojectpostapi.java.model.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        recyclerView= findViewById(R.id.recyclerViewHome);
        apiResponse();
    }

    private void apiResponse() {
        apiInterface.getPost().enqueue(new Callback<List<ApiResponse>>() {
            @Override
            public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                if (response.body().size()>0){
                    List<ApiResponse> postList= response.body();
                    RecyclerAdapter adapter= new RecyclerAdapter(postList);
                    LinearLayoutManager linearLayoutManager= new LinearLayoutManager(HomeActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(HomeActivity.this, "List is not  empty", Toast.LENGTH_LONG).show();
                    //Log.d("javaApiCalling",arraylist);


                }else {
                    Toast.makeText(HomeActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<ApiResponse>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}