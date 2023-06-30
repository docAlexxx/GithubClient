package com.gb.poplib.githubclient.mvp.model.repo.cashe

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.entity.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepoCashe(val db: Database) : IRepoCashe {
    override fun setRepos(user: GithubUser, repos: List<UserRepos>): Completable =
        Completable.fromAction {
            val roomUser =
                db.userDao.findByLogin(user.login)
                    ?: throw RuntimeException("No such user in cache")

            val roomRepos = repos.map {
                RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.watchers ?: 0,
                    it.forks ?: 0,
                    it.language ?: "",
                    roomUser.id
                )
            }

            db.repositoryDao.insert(roomRepos)
        }.subscribeOn(Schedulers.io())

    override fun getRepos(user: GithubUser): Single<List<UserRepos>> =
        Single.fromCallable {
            val roomUser =
                db.userDao.findByLogin(user.login)
                    ?: throw RuntimeException("No such user in cache")

            return@fromCallable db.repositoryDao.findForUser(roomUser.id).map {
                UserRepos(it.id, it.name, it.watchers, it.forks, it.language)
            }
        }.subscribeOn(Schedulers.io())
}