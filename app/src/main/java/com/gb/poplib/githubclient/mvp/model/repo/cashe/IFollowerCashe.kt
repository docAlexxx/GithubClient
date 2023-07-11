package com.gb.poplib.githubclient.mvp.model.repo.cashe

import com.gb.poplib.githubclient.mvp.model.entity.Follower
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IFollowerCashe {
    fun setFollowers(user: GithubUser, followers: List<Follower>): Completable
    fun getFollowers(user: GithubUser): Single<List<Follower>>
}