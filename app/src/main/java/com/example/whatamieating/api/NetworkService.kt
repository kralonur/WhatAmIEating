package com.example.whatamieating.api

import com.example.whatamieating.api.service.RecipeService
import com.example.whatamieating.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

private fun getLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun getHTTPClient(): OkHttpClient {
    Timber.i("getHTTPClient called")
    return OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .addInterceptor(getLoggingInterceptor())
        .build()
}

private fun getMoshi(): Moshi {
    Timber.i("getMoshi called")
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

private fun getRetrofit(): Retrofit {
    Timber.i("getRetrofit called")
    return Retrofit.Builder()
        .client(getHTTPClient())
        .baseUrl(Constants.Api.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
        .build()
}

private inline fun <reified T> createService(): T =
    getRetrofit().create(T::class.java)

object NetworkService {
    fun getRecipeService() = createService<RecipeService>()
}