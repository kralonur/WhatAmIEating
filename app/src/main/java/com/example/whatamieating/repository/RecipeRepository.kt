package com.example.whatamieating.repository

import com.example.whatamieating.model.domain.RecipeInformation
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.model.remote.SearchRecipesResponse
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun searchRecipe(query: String): Flow<ResultWrapper<SearchRecipesResponse>>
    fun getRecipeInformation(recipeId: Int): Flow<ResultWrapper<RecipeInformation>>
}