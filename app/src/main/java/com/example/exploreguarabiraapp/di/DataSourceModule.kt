package com.example.exploreguarabiraapp.di

import android.content.Context
import com.example.exploreguarabiraapp.data.LocalDataSource
import com.example.exploreguarabiraapp.data.datasource.JsonLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context
    ): LocalDataSource {
        return JsonLocalDataSource(context)
    }
}