package com.codinginflow.animeapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codinginflow.animeapp.model.Repository
import com.codinginflow.animeapp.model.ResultHero
import com.codinginflow.animeapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewMode @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    var recipesResponse: MutableLiveData<NetworkResult<ResultHero>> = MutableLiveData()
    var searchedRecipesResponse: MutableLiveData<NetworkResult<ResultHero>> = MutableLiveData()

    fun getRecipes(queries: Int) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    fun searchRecipes(searchQuery: String) = viewModelScope.launch {
        searchRecipesSafeCall(searchQuery)
    }

    private suspend fun getRecipesSafeCall(queries: Int) {
        recipesResponse.value = NetworkResult.Loading()
        try {
            val response = repository.remote.getRecipes(queries)
            recipesResponse.value = handleCoroutineResponse(response)

        } catch (e: Exception) {
            recipesResponse.value = NetworkResult.Error("Recipes not found.")
        }
    }

    private suspend fun searchRecipesSafeCall(searchQuery: String) {
        Log.e("ABCD", "3")
        searchedRecipesResponse.value = NetworkResult.Loading()
        try {
            val response = repository.remote.searchRecipes(searchQuery)
            Log.e("ABCD", response.toString())
            searchedRecipesResponse.value = handleCoroutineResponse(response)
            Log.e("ABCD", "6")

        } catch (e: Exception) {
            Log.e("ABCD", e.toString())
            searchedRecipesResponse.value = NetworkResult.Error("Recipes not found.")
        }
    }

    private fun handleCoroutineResponse(response: Response<ResultHero>): NetworkResult<ResultHero> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.heroes.isEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful -> {
                val resultHero = response.body()
                return  NetworkResult.Success(resultHero!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

}