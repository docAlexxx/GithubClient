package com.gb.poplib.githubclient.di.modules

import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.poplib.githubclient.mvp.model.repo.cashe.IRepoCashe
import com.gb.poplib.githubclient.mvp.model.repo.cashe.IUserCashe
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cashe: IUserCashe)
            : IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cashe)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cashe: IRepoCashe)
            : IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cashe)
}