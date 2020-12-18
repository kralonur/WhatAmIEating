package com.example.whatamieating.ui.recipe_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.whatamieating.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers

class RecipeListViewModel @ViewModelInject constructor(
    private val repo: RecipeRepository
) : ViewModel() {

    fun searchRecipe(query: String) =
        repo.searchRecipe(query).asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)

}