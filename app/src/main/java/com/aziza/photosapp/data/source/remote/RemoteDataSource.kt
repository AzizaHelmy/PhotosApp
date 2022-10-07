package com.aziza.photosapp.data.source.remote

import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val networkService: NetworkService) :
    IRemoteDataSource {

    override suspend fun getPhotosByAlbumId( albumId:Int): Response<List<Photo>> = networkService.getPhotosByAlbumId(albumId)

}