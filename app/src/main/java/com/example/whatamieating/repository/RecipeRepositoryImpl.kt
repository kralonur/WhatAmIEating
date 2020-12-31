package com.example.whatamieating.repository

import com.example.whatamieating.api.service.RecipeService
import com.example.whatamieating.model.mapper.RecipeInformationRemoteToDomainMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val api: RecipeService
) : BaseRepository(), RecipeRepository {

    override fun searchRecipe(query: String) = flowCall {
        api.searchRecipe(query)
    }

    override fun getRecipeInformation(recipeId: Int) = flowCall {
        val getRecipeInformationResponse = api.getRecipeInformation(recipeId, true)
        RecipeInformationRemoteToDomainMapper().map(getRecipeInformationResponse)
    }


}