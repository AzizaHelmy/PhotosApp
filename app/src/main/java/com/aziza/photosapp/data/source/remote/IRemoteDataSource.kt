package com.aziza.photosapp.data.source.remote

import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response

interface IRemoteDataSource {
    suspend fun getAllPhotos(): Response<List<Photo>>
}