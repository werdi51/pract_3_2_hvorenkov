package com.example.pract_3_2_hvorenkov

import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") searchQuery:String,
        @Query ("apikey") apiKey:String="896c83b3"
    ): MovieSearch

}