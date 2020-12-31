package com.example.whatamieating.model.domain

import com.squareup.moshi.Json

data class RecipeInformation(
    val likesCount: Int,
    val dishImageUrl: String,
    val cookingTime: Int,
    val dishName: String,
    val dishId: Int,
    val creditText: String,
    val shortInstruction: String,
    val summary: String,
    val steps: List<Step>,
    val nutrients: List<Nutrient>,
    val ingredients: List<Ingredient>,
    val properties: List<Property>,
    val flavanoids: List<Flavanoid>,
    val caloricBreakdown: CaloricBreakdown,
    val isGlutenFree: Boolean,
    val isDairyFree: Boolean,
    val isVegetarian: Boolean
)

data class Step(
    val number: Int,
    val stepInstruction: String
)

data class Nutrient(
    val title: String,
    val amount: Double,
    val unit: String,
    val percentOfDailyNeeds: Double
)

data class Ingredient(
    val id: Int,
    val imageHalfPath: String,
    val name: String,
    val desc: String
)

data class Property(
    @Json(name = "amount") val amount: Double,
    @Json(name = "title") val title: String,
    @Json(name = "unit") val unit: String
)

data class Flavanoid(
    @Json(name = "amount") val amount: Double,
    @Json(name = "title") val title: String,
    @Json(name = "unit") val unit: String
)

data class CaloricBreakdown(
    @Json(name = "percentProtein") val percentProtein: Double,
    @Json(name = "percentFat") val percentFat: Double,
    @Json(name = "percentCarbs") val percentCarbs: Double
)