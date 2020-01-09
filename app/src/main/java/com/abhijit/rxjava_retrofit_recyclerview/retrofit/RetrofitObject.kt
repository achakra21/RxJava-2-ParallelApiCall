package com.abhijit.rxjava_retrofit_recyclerview.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    private var retrofitInstance : Retrofit?=null
    val instance:Retrofit
    get() {

        if(retrofitInstance == null){
            retrofitInstance = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        }
        return retrofitInstance!!
    }


}