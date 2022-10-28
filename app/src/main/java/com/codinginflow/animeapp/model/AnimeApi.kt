package com.codinginflow.animeapp.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AnimeApi {

    @GET("/boruto/heroes")
    suspend fun getRecipes(
        @Query("page") page: Int = 1
    ): Response<ResultHero>

    @GET("/boruto/heroes/search")
    suspend fun searchRecipes(
        @Query("name") name: String
    ): Response<ResultHero>

}