package com.example.whatamieating

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@Suppress("unused")
@HiltAndroidApp
class Application : Application() {


    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

    }

}