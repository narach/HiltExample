package com.example.hiltexample.repository

import com.example.hiltexample.api.CarsApiHelper
import javax.inject.Inject

class CarsRepository @Inject constructor(
    private val carsApiHelper: CarsApiHelper // Injecting helper into repository - the same way as for ApiHelper & ApiService
) {
    suspend fun getCars() = carsApiHelper.getCars()
}