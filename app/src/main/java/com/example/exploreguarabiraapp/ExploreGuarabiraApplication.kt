package com.example.exploreguarabiraapp

import android.app.Application
import com.example.exploreguarabiraapp.di.AppContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExploreGuarabiraApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}