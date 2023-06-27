package com.gb.poplib.githubclient

import android.app.Application
import com.gb.poplib.githubclient.navigation.Screens
import com.gb.poplib.githubclient.navigation.ToolScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App: Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    val screens : Screens = ToolScreens()

}