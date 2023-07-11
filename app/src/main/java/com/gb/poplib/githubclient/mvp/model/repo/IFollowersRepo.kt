package com.gb.poplib.githubclient.mvp.model.repo

import com.gb.poplib.githubclient.mvp.model.entity.Follower
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import io.reactivex.rxjava3.core.Single

interface IFollowersRepo {
    fun getFollowers(user: GithubUser): Single<List<Follower>>
}