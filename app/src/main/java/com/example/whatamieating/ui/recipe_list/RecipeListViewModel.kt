package com.example.whatamieating.ui.recipe_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.whatamieating.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers

class RecipeListViewModel @ViewModelInject constructor(
    private val repo: RecipeRepository
) : ViewModel() {

    private val _recipeQuery = MutableLiveData<String>()
    val recipeQuery: LiveData<String>
        get() = _recipeQuery

    val recipe =
        Transformations.switchMap(recipeQuery) {
            searchRecipe(it)
        }

    private fun searchRecipe(query: String) =
        repo.searchRecipe(query).asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)

    fun setRecipeQuery(query: String) {
        _recipeQuery.postValue(query)
    }

}