package com.gustainmars.gdcapplication

import android.app.Application

class GdcApplication : Application() {

    companion object{
        lateinit var instance: GdcApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}