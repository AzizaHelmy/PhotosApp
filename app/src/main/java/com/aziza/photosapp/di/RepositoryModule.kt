package com.aziza.photosapp.di

import com.aziza.photosapp.data.repo.IRepository
import com.aziza.photosapp.data.repo.RepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideRepoInstance(repo: RepositoryImp):IRepository
}