package com.gb.poplib.githubclient.mvp.model.repo.cashe

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRepoCashe {
    fun setRepos(user: GithubUser, repos: List<UserRepos>): Completable
    fun getRepos(user: GithubUser): Single<List<UserRepos>>
}