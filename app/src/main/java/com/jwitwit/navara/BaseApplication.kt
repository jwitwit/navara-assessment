package com.jwitwit.navara

import android.app.Application
import timber.log.Timber

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}