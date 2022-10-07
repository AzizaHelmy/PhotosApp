package com.aziza.photosapp.data.repo

import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response
import retrofit2.http.Path

interface IRepository {
    suspend fun getPhotosByAlbumId(albumId:Int): Response<List<Photo>>
}