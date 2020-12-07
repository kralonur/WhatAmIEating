package com.example.whatamieating.model.remote

import com.squareup.moshi.Json

data class SearchRecipesResponse(
    @Json(name = "results") val results: List<Result>,
    @Json(name = "offset") val offset: Int,
    @Json(name = "number") val number: Int,
    @Json(name = "totalResults") val totalResults: Int
)

data class Result(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "image") val image: String,
    @Json(name = "imageType") val imageType: String
)
