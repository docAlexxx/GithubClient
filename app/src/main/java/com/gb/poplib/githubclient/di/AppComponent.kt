package com.gb.poplib.githubclient.di

import com.gb.poplib.githubclient.di.modules.ApiModule
import com.gb.poplib.githubclient.di.modules.AppModule
import com.gb.poplib.githubclient.di.modules.CacheModule
import com.gb.poplib.githubclient.di.modules.CiceroneModule
import com.gb.poplib.githubclient.di.modules.RepoModule
import com.gb.poplib.githubclient.mvp.presenter.MainPresenter
import com.gb.poplib.githubclient.mvp.presenter.UserPresenter
import com.gb.poplib.githubclient.ui.activity.MainActivity
import com.gb.poplib.githubclient.ui.fragment.RepoItemFragment
import com.gb.poplib.githubclient.ui.fragment.UserItemFragment
import com.gb.poplib.githubclient.ui.fragment.UsersListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(userPresenter: UserPresenter)


    fun inject(usersListFragment: UsersListFragment)
    fun inject(userItemFragment: UserItemFragment)
    fun inject(repoItemFragment: RepoItemFragment)
}