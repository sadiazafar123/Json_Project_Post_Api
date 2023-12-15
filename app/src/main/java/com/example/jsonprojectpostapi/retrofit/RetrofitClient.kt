package com.example.jsonprojectpostapi.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
 fun getInstance():ApiInterface{
     var mHttpLoggingInterceptor= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
     val gson= GsonBuilder().setLenient().create()
     //create okhttp client
     var mOkHttpClient=OkHttpClient.Builder()
     val loggingInterceptor= getLoggingInterceptor()
     loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY
     mOkHttpClient.addInterceptor(loggingInterceptor)
     //create retrofit builder
     var  retrofit: Retrofit = retrofit2.Retrofit.Builder().baseUrl("https://papergen.easyautopaper.com/api/")
         .addConverterFactory(GsonConverterFactory.create(gson)).client(mOkHttpClient.build()).build()
     return retrofit.create(ApiInterface::class.java)



 }
    private  fun getLoggingInterceptor():HttpLoggingInterceptor{
        val loggingInterceptor= HttpLoggingInterceptor()
        loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}