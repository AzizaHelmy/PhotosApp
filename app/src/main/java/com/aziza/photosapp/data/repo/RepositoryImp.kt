package com.aziza.photosapp.data.repo

import com.aziza.photosapp.data.source.remote.IRemoteDataSource
import com.aziza.photosapp.ui.home.Photo
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val remoteDataSource: IRemoteDataSource) :
    IRepository {


    override suspend fun getPhotosByAlbumId( albumId:Int): Response<List<Photo>> {
        return  remoteDataSource.getPhotosByAlbumId(albumId)
    }
}