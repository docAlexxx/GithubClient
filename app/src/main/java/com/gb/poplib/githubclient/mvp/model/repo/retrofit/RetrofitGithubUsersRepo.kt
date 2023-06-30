package com.gb.poplib.githubclient.mvp.model.repo.retrofit

import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.poplib.githubclient.mvp.model.repo.cashe.IUserCashe
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    private val cashe: IUserCashe
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                cashe.setUsers(users).toSingleDefault(users)
            }
        } else {
            cashe.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}