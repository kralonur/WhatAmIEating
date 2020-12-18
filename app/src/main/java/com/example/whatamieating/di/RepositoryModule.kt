package com.example.whatamieating.di

import com.example.whatamieating.repository.RecipeRepository
import com.example.whatamieating.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {
    @Binds
    fun provideRecipeRepositoryImpl(repository: RecipeRepositoryImpl): RecipeRepository
}