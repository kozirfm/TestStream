package com.example.teststream.app.di

import com.example.teststream.datasource.RemoteDataSource
import com.example.teststream.datasource.RemoteDataSourceImplementation
import com.example.teststream.model.retrofit.RetrofitService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(api: RetrofitService): RemoteDataSource =
        RemoteDataSourceImplementation(api = api)
}