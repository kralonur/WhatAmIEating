package com.example.whatamieating.di

import com.example.whatamieating.repository.RecipeRepository
import com.example.whatamieating.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideRecipeRepositoryImpl(repository: RecipeRepositoryImpl): RecipeRepository
}