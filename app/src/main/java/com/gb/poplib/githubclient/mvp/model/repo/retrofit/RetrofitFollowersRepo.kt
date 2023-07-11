package com.gb.poplib.githubclient.mvp.model.repo.retrofit

import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.Follower
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IFollowersRepo
import com.gb.poplib.githubclient.mvp.model.repo.cashe.IFollowerCashe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitFollowersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    private val cashe: IFollowerCashe
) : IFollowersRepo {
    override fun getFollowers(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
               user.followersUrl?.let { url ->

                    api.getFollowers(url).flatMap { followers ->
                        cashe.setFollowers(user, followers).toSingleDefault(followers)
                    }
                } ?: Single.error<List<Follower>>(RuntimeException("User has no followers url"))
                    .subscribeOn(
                        Schedulers.io()
                    )
            } else {
                cashe.getFollowers(user)
            }
        }.subscribeOn(Schedulers.io())

}