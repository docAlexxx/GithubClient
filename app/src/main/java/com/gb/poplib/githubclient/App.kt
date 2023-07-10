package com.gb.poplib.githubclient

import android.app.Application
import com.gb.poplib.githubclient.di.AppComponent
import com.gb.poplib.githubclient.di.DaggerAppComponent
import com.gb.poplib.githubclient.di.modules.AppModule
import com.gb.poplib.githubclient.di.repo.RepoSubcomponent
import com.gb.poplib.githubclient.di.repo.modules.IRepoScopeContainer
import com.gb.poplib.githubclient.di.user.Modules.IUserScopeContainer
import com.gb.poplib.githubclient.di.user.UserSubcomponent

class App : Application(), IUserScopeContainer, IRepoScopeContainer {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repoSubcomponent: RepoSubcomponent? = null
        private set

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepoSubcomponent() = userSubcomponent?.repoSubcomponent().also {
        repoSubcomponent = it
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun releaseRepoScope() {
        repoSubcomponent = null
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }


}