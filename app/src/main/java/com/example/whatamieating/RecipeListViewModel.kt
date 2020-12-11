package com.example.whatamieating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.whatamieating.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers

class RecipeListViewModel : ViewModel() {
    private val repo = RecipeRepository()

    fun searchRecipe(query: String) =
        repo.searchRecipe(query).asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)

}