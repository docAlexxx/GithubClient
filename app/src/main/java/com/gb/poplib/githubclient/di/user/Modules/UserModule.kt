package com.gb.poplib.githubclient.di.user.Modules

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.di.user.UserScope
import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.poplib.githubclient.mvp.model.repo.cashe.IUserCashe
import com.gb.poplib.githubclient.mvp.model.repo.cashe.UserCashe
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun usersCache(database: Database): IUserCashe = UserCashe(database)

    @UserScope
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cashe: IUserCashe)
            : IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cashe)

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}