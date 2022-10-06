package com.aziza.photosapp.data.repo

import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response

interface IRepository {
    suspend fun getAllPhotos(): Response<List<Photo>>
}