package com.example.whatamieating.api.service

import com.example.whatamieating.model.remote.GetRecipeInformationResponse
import com.example.whatamieating.model.remote.SearchRecipesResponse
import com.example.whatamieating.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {
    @GET(Constants.Api.RECIPE_INFORMATION)
    suspend fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("includeNutrition") isIncludeNutrition: Boolean
    ): GetRecipeInformationResponse

    @GET(Constants.Api.SEARCH_RECIPE)
    suspend fun searchRecipe(
        @Query("query") query: String
    ): SearchRecipesResponse
}