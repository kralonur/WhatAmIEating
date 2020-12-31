package com.example.whatamieating.model.remote

import com.squareup.moshi.Json

data class GetRecipeInformationResponse(
    @Json(name = "vegetarian") val vegetarian: Boolean?,
    @Json(name = "vegan") val vegan: Boolean?,
    @Json(name = "glutenFree") val glutenFree: Boolean?,
    @Json(name = "dairyFree") val dairyFree: Boolean?,
    @Json(name = "ketogenic") val ketogenic: Boolean?,
    @Json(name = "whole30") val whole30: Boolean?,
    @Json(name = "veryHealthy") val veryHealthy: Boolean?,
    @Json(name = "cheap") val cheap: Boolean?,
    @Json(name = "veryPopular") val veryPopular: Boolean?,
    @Json(name = "sustainable") val sustainable: Boolean?,
    @Json(name = "weightWatcherSmartPoints") val weightWatcherSmartPoints: Int?,
    @Json(name = "gaps") val gaps: String?,
    @Json(name = "lowFodmap") val lowFodmap: Boolean?,
    @Json(name = "aggregateLikes") val aggregateLikes: Int?,
    @Json(name = "spoonacularScore") val spoonacularScore: Double?,
    @Json(name = "healthScore") val healthScore: Double?,
    @Json(name = "creditsText") val creditText: String?,
    @Json(name = "license") val license: String?,
    @Json(name = "sourceName") val sourceName: String?,
    @Json(name = "pricePerServing") val pricePerServing: Double?,
    @Json(name = "extendedIngredients") val extendedIngredients: List<Ingredient>?,
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String?,
    @Json(name = "readyInMinutes") val readyInMinutes: Int?,
    @Json(name = "servings") val servings: Int?,
    @Json(name = "sourceUrl") val sourceUrl: String?,
    @Json(name = "image") val image: String?,
    @Json(name = "imageType") val imageType: String?,
    @Json(name = "nutrition") val nutrition: Nutrition?,
    @Json(name = "summary") val summary: String?,
    @Json(name = "cuisines") val cuisines: List<String>?,
    @Json(name = "dishTypes") val dishTypes: List<String>?,
    @Json(name = "diets") val diets: List<String>?,
    @Json(name = "occasions") val occasions: List<String>?,
    @Json(name = "instructions") val instructions: String?,
    @Json(name = "analyzedInstructions") val analyzedInstructions: List<Instruction>?,
    @Json(name = "originalId") val originalId: Int?,
    @Json(name = "spoonacularSourceUrl") val spoonacularSourceUrl: String?
)

data class Ingredient(
    @Json(name = "id") val id: Int?,
    @Json(name = "image") val image: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "original") val original: String?
)

data class Instruction(
    @Json(name = "steps") val steps: List<Step>?
)

data class Step(
    @Json(name = "number") val number: Int?,
    @Json(name = "step") val step: String?
)

data class Nutrition(
    @Json(name = "nutrients") val nutrients: List<Nutrient>?,
    @Json(name = "properties") val properties: List<Property>?,
    @Json(name = "flavanoids") val flavanoids: List<Flavanoid>?,
    @Json(name = "caloricBreakdown") val caloricBreakdown: CaloricBreakdown?
)

data class Nutrient(
    @Json(name = "amount") val amount: Double?,
    @Json(name = "percentOfDailyNeeds") val percentOfDailyNeeds: Double?,
    @Json(name = "title") val title: String?,
    @Json(name = "unit") val unit: String?
)

data class Property(
    @Json(name = "amount") val amount: Double?,
    @Json(name = "title") val title: String?,
    @Json(name = "unit") val unit: String?
)

data class Flavanoid(
    @Json(name = "amount") val amount: Double?,
    @Json(name = "title") val title: String?,
    @Json(name = "unit") val unit: String?
)

data class CaloricBreakdown(
    @Json(name = "percentProtein") val percentProtein: Double?,
    @Json(name = "percentFat") val percentFat: Double?,
    @Json(name = "percentCarbs") val percentCarbs: Double?
)