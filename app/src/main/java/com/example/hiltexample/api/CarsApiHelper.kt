package com.example.hiltexample.api

import com.example.hiltexample.models.CarsResponse
import retrofit2.Response

interface CarsApiHelper {
    suspend fun getCars() : Response<CarsResponse>
}