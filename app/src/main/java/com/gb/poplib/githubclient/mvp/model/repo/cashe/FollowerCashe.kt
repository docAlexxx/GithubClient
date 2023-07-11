package com.gb.poplib.githubclient.mvp.model.repo.cashe

import com.gb.poplib.githubclient.mvp.model.entity.Follower
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.entity.room.RoomFollower
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FollowerCashe(val db: Database) : IFollowerCashe {
    override fun setFollowers(user: GithubUser, followers: List<Follower>): Completable =
        Completable.fromAction {
            val roomUser =
                db.userDao.findByLogin(user.login)
                    ?: throw RuntimeException("No such user in cache")

            val roomFollowers = followers.map {
                RoomFollower(
                    it.id ?: "",
                    it.login ?: "",
                    it.avatarUrl ?: "",
                    roomUser.id
                )
            }

            db.followerDao.insert(roomFollowers)
        }.subscribeOn(Schedulers.io())

    override fun getFollowers(user: GithubUser): Single<List<Follower>> =
        Single.fromCallable {
            val roomUser =
                db.userDao.findByLogin(user.login)
                    ?: throw RuntimeException("No such user in cache")

            return@fromCallable db.followerDao.findForUser(roomUser.id).map {
                Follower(it.id, it.login, it.avatarUrl)
            }
        }.subscribeOn(Schedulers.io())
}