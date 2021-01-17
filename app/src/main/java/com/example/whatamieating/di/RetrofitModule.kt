package com.example.whatamieating.di

import com.example.whatamieating.BuildConfig
import com.example.whatamieating.api.service.RecipeService
import com.example.whatamieating.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideHTTPInterceptor(): Interceptor {
        return Interceptor { chain ->
            val httpUrl = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("apiKey", BuildConfig.SPOONACULAR_API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(httpUrl)
                .build()

            chain.proceed(request)
        }
    }


    @Singleton
    @Provides
    fun provideHTTPClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit {
        Timber.i("getRetrofit called")
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(Constants.Api.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }
}