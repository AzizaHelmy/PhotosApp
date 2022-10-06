package com.aziza.photosapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aziza.photosapp.data.repo.IRepository
import com.aziza.photosapp.data.repo.RepositoryImp
import com.aziza.photosapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: IRepository) : ViewModel() {
    private val _photoResult= MutableLiveData<List<Photo>>()
    var photoResult: LiveData<List<Photo>> = _photoResult

    fun getAllPhoto() = viewModelScope.launch {
        val result = repo.getAllPhotos()
        _photoResult.postValue(result.body())

    }


}