package com.gb.poplib.githubclient.mvp.model.repo.cashe

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserCashe(private val db: Database) : IUserCashe {
    override fun setUsers(users: List<GithubUser>): Completable = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id,
                user.login,
                user.avatarUrl ?: "",
                user.reposUrl ?: "",
                user.followersUrl ?: ""
            )

        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    override fun getUsers(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.id,
                roomUser.login,
                roomUser.avatarUrl,
                roomUser.reposUrl,
                roomUser.followersUrl
            )
        }
    }.subscribeOn(Schedulers.io())
}