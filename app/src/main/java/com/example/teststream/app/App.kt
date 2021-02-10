package com.example.teststream.app

import android.app.Application
import com.example.teststream.app.di.AppComponent
import com.example.teststream.app.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }

}