package com.example.exploreguarabiraapp.di

import android.content.Context
import com.example.exploreguarabiraapp.data.datasource.JsonLocalDataSource
import com.example.exploreguarabiraapp.data.repository.LocalRepository
import com.example.exploreguarabiraapp.data.repository.LocalRepositoryImpl
import com.example.exploreguarabiraapp.data.repository.fake.FakeLocalRepository

class AppContainer(context: Context) {

    private val jsonDataSource = JsonLocalDataSource(context)

    val localRepository: LocalRepository =
        LocalRepositoryImpl(jsonDataSource)
}