package com.example.whatamieating.api

import com.example.whatamieating.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("apiKey", BuildConfig.SPOONACULAR_API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(httpUrl)
            .build()

        return chain.proceed(request)
    }
}