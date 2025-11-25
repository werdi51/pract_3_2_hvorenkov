package com.example.pract_3_2_hvorenkov

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRealization {
    private const val URL= "http://www.omdbapi.com/"
    private const val API="896c83b3"

private val retrofit:Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
    val api :OMDbApiService by lazy {
        retrofit.create(OMDbApiService::class.java)
    }
}