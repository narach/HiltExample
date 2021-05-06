package com.example.hiltexample.di

import androidx.viewbinding.BuildConfig
import com.example.hiltexample.api.CarsApiHelper
import com.example.hiltexample.api.CarsApiHelperImpl
import com.example.hiltexample.api.CarsApiService
import com.example.hiltexample.other.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module // Marks as Dagger Hilt Module
@InstallIn(ApplicationComponent::class) // Objects will be available from any part of application
object AppModule {

    @Provides // Inject base server url
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton // Create single instance of HttpClient
    @Provides
    fun provideOkHttpClient() =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor= HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient.Builder().build()
        }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String) : Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideCarsApiService(retrofit: Retrofit) =
        retrofit.create(CarsApiService::class.java)

    @Provides
    @Singleton
    fun provideCarsApiHelper(apiService: CarsApiService) : CarsApiHelper =
        CarsApiHelperImpl(carsApiService = apiService)
}