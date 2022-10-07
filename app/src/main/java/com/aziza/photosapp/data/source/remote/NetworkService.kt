package com.aziza.photosapp.data.source.remote

import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("photos?")
    suspend fun getPhotosByAlbumId(@Query("albumId") albumId:Int): Response<List<Photo>>

}