package com.gb.poplib.githubclient

import android.app.Application
import com.gb.poplib.githubclient.di.AppComponent
import com.gb.poplib.githubclient.di.DaggerAppComponent
import com.gb.poplib.githubclient.di.modules.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


}