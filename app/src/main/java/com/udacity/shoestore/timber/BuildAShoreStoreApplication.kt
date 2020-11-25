package com.udacity.shoestore.timber

import android.app.Application
import timber.log.Timber

class BuildAShoreStoreApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}