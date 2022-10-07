package com.aziza.photosapp.data.source.remote

import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("photos")
    suspend fun getAllPhotos():Response<List<Photo>>
    
}