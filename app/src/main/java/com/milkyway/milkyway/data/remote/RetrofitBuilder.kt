package com.milkyway.milkyway.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private const val baseURL = "http://18.217.155.194/"

    private var retrofit = Retrofit.Builder().baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service : MilkyWayService = retrofit.create(MilkyWayService::class.java)
}