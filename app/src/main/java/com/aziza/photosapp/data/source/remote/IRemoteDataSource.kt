package com.aziza.photosapp.data.source.remote

import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response
import retrofit2.http.Path

interface IRemoteDataSource {
    suspend fun getPhotosByAlbumId(albumId:Int): Response<List<Photo>>
}