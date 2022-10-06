package com.aziza.photosapp.data.source.remote

import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val networkService: NetworkService) :
    IRemoteDataSource {

    override suspend fun getAllPhotos(): Response<List<Photo>> = networkService.getAllPhotos()

}