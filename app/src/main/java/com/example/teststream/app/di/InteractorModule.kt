package com.example.teststream.app.di

import com.example.teststream.datasource.RemoteDataSource
import com.example.teststream.model.interactors.Interactor
import com.example.teststream.model.interactors.InteractorImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun providesMainInteractor(remoteDataSource: RemoteDataSource): Interactor =
        InteractorImplementation(remoteDataSource = remoteDataSource)
}