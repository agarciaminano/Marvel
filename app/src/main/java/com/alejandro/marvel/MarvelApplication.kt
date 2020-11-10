package com.alejandro.marvel

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("ON CREATE", "Application onCreate")

    }
}