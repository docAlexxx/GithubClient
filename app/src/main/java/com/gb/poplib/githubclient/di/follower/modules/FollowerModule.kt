package com.gb.poplib.githubclient.di.follower.modules

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.di.repo.FollowerScope
import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IFollowersRepo
import com.gb.poplib.githubclient.mvp.model.repo.cashe.FollowerCashe
import com.gb.poplib.githubclient.mvp.model.repo.cashe.IFollowerCashe
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitFollowersRepo
import dagger.Module
import dagger.Provides

@Module
class FollowerModule {

    @Provides
    fun followersCache(database: Database): IFollowerCashe {
        return FollowerCashe(database)
    }

    @FollowerScope
    @Provides
    fun followersRepo(api: IDataSource, networkStatus: INetworkStatus, cashe: IFollowerCashe)
            : IFollowersRepo = RetrofitFollowersRepo(api, networkStatus, cashe)

    @FollowerScope
    @Provides
    open fun scopeContainer(app: App): IFollowerScopeContainer = app

}