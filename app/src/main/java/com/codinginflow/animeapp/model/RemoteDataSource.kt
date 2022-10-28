package com.codinginflow.animeapp.model

import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: AnimeApi
) {

    suspend fun getRecipes(queries: Int) : Response<ResultHero> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: String): Response<ResultHero> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

}