package com.aziza.photosapp.di

import com.aziza.photosapp.data.source.remote.IRemoteDataSource
import com.aziza.photosapp.data.source.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun provideRemoteDataSourceInstance(remoteDataSource: RemoteDataSource): IRemoteDataSource
}