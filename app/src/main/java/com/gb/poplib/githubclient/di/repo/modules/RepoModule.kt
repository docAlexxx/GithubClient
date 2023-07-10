package com.gb.poplib.githubclient.di.repo.modules

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.di.repo.RepoScope
import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.gb.poplib.githubclient.mvp.model.repo.cashe.IRepoCashe
import com.gb.poplib.githubclient.mvp.model.repo.cashe.RepoCashe
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun repositoriesCache(database: Database): IRepoCashe {
        return RepoCashe(database)
    }

    @RepoScope
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cashe: IRepoCashe)
            : IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cashe)

    @RepoScope
    @Provides
    open fun scopeContainer(app: App): IRepoScopeContainer = app
}