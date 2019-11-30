package com.daya.katalogfilm

import android.app.Application
import com.facebook.stetho.Stetho

class Katalog : Application(){

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}