package com.example.whatamieating.ui.recipe_information

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.whatamieating.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers

class RecipeInformationViewModel @ViewModelInject constructor(
    private val repo: RecipeRepository
) : ViewModel() {

    private val _recipeId = MutableLiveData<Int>()
    val recipeId: LiveData<Int>
        get() = _recipeId


    fun updateRecipeId(recipeId: Int) {
        _recipeId.postValue(recipeId)
    }

    val recipeInfo = Transformations.switchMap(recipeId) {
        getRecipeInfo(it)
    }


    private fun getRecipeInfo(recipeId: Int) =
        repo.getRecipeInformation(recipeId)
            .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)

}