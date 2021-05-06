package com.example.hiltexample.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltexample.models.CarsResponse
import com.example.hiltexample.other.Resource
import com.example.hiltexample.repository.CarsRepository
import kotlinx.coroutines.launch

class CarsViewModel @ViewModelInject constructor(
    private val carsRepository: CarsRepository // Inject repository into ViewModel
) : ViewModel() {

    // LiveData to store response from server
    private val _res = MutableLiveData<Resource<CarsResponse>>()

    // Just a recommended by official guide way to provide LiveData from ViewModel
    val res : LiveData<Resource<CarsResponse>>
        get() = _res

    // Receive cars list just when the ViewModel is initialized
//    init {
//        getCars()
//    }

    // Receive cars from server here - Running in a separate Thread
    fun getCars() {
        viewModelScope.launch {
            _res.postValue(Resource.loading(null))
            carsRepository.getCars().let { response ->
                if(response.isSuccessful) {
                    _res.postValue(Resource.success(
                        response.body()))
                } else {
                    _res.postValue(Resource.error(
                        response.errorBody().toString(), null
                    ))
                }
            }
        }
    }
}