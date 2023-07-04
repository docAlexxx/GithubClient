package com.gb.poplib.githubclient.di

import com.gb.poplib.githubclient.di.modules.ApiModule
import com.gb.poplib.githubclient.di.modules.AppModule
import com.gb.poplib.githubclient.di.modules.CacheModule
import com.gb.poplib.githubclient.di.modules.CiceroneModule
import com.gb.poplib.githubclient.di.modules.GlideModule
import com.gb.poplib.githubclient.di.modules.RepoModule
import com.gb.poplib.githubclient.mvp.presenter.MainPresenter
import com.gb.poplib.githubclient.mvp.presenter.RepoItemPresenter
import com.gb.poplib.githubclient.mvp.presenter.UserItemPresenter
import com.gb.poplib.githubclient.mvp.presenter.UserPresenter
import com.gb.poplib.githubclient.ui.activity.MainActivity
import com.gb.poplib.githubclient.ui.adapter.UserAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        RepoModule::class,
        GlideModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(userPresenter: UserPresenter)

    fun inject(userItemPresenter: UserItemPresenter)
    fun inject(repoItemPresenter: RepoItemPresenter)
    fun inject(userAdapter: UserAdapter)

}