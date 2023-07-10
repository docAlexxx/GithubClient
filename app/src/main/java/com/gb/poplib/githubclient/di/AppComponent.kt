package com.gb.poplib.githubclient.di

import com.gb.poplib.githubclient.di.modules.ApiModule
import com.gb.poplib.githubclient.di.modules.AppModule
import com.gb.poplib.githubclient.di.modules.CacheModule
import com.gb.poplib.githubclient.di.modules.CiceroneModule
import com.gb.poplib.githubclient.di.modules.GlideModule
import com.gb.poplib.githubclient.di.user.UserSubcomponent
import com.gb.poplib.githubclient.mvp.presenter.MainPresenter
import com.gb.poplib.githubclient.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        GlideModule::class
    ]
)
interface AppComponent {
    fun userSubcomponent(): UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)


}