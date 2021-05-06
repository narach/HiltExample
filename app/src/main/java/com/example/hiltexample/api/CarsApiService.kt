package com.example.hiltexample.api

import com.example.hiltexample.models.CarsResponse
import retrofit2.Response
import retrofit2.http.GET

interface CarsApiService {

    @GET("cars")
    suspend fun getCars() : Response<CarsResponse>
}