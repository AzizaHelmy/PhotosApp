package com.aziza.photosapp.data.repo

import com.aziza.photosapp.data.source.remote.IRemoteDataSource
import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val remoteDataSource: IRemoteDataSource) :
    IRepository {


    override suspend fun getAllPhotos(): Response<List<Photo>> {
        return  remoteDataSource.getAllPhotos()
    }
}