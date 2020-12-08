package com.example.whatamieating.repository

import com.example.whatamieating.api.NetworkService
import com.example.whatamieating.model.mapper.RecipeInformationRemoteToDomainMapper

class RecipeRepository : BaseRepository() {
    private val api = NetworkService.getRecipeService()

    fun searchRecipe(query: String) = flowCall {
        api.searchRecipe(query)
    }

    fun getRecipeInformation(recipeId: Int) = flowCall {
        val getRecipeInformationResponse = api.getRecipeInformation(recipeId, true)
        RecipeInformationRemoteToDomainMapper().map(getRecipeInformationResponse)
    }
}