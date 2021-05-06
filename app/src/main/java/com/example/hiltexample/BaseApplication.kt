package com.example.hiltexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Define, that we are going to use Hilt in App
class BaseApplication: Application() {
}