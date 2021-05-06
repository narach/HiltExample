package com.example.hiltexample.api

import com.example.hiltexample.models.CarsResponse
import retrofit2.Response
import javax.inject.Inject

class CarsApiHelperImpl @Inject constructor(
    private val carsApiService: CarsApiService // Inject apiService into helper, so only one instance of api will exist
) : CarsApiHelper {

    override suspend fun getCars(): Response<CarsResponse> {
        return carsApiService.getCars()
    }
}