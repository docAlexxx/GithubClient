package com.gb.poplib.githubclient.mvp.model.repo.cashe

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUserCashe {
    fun setUsers(users: List<GithubUser>): Completable
    fun getUsers(): Single<List<GithubUser>>
}