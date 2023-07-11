package com.gb.poplib.githubclient.mvp.model.api

import com.gb.poplib.githubclient.mvp.model.entity.Follower
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepos(@Url reposUrl: String): Single<List<UserRepos>>

    @GET
    fun getFollowers(@Url followersUrl: String): Single<List<Follower>>
}