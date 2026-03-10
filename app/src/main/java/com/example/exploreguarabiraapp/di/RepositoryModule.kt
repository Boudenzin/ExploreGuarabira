package com.example.exploreguarabiraapp.di

import android.content.Context
import com.example.exploreguarabiraapp.data.LocalDataSource
import com.example.exploreguarabiraapp.data.repository.ImageRepository
import com.example.exploreguarabiraapp.data.repository.ImageRepositoryImpl
import com.example.exploreguarabiraapp.data.repository.LocalRepository
import com.example.exploreguarabiraapp.data.repository.LocalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalRepository(
        dataSource: LocalDataSource
    ): LocalRepository =
        LocalRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideImageRepository(
        @ApplicationContext context: Context
    ): ImageRepository {
        return ImageRepositoryImpl(context)
    }
}
