package com.gb.poplib.githubclient.mvp.model.repo.retrofit

import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.GitHubUserRepo
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.entity.room.RoomGithubRepository
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(val api: IDataSource,
                                     val networkStatus: INetworkStatus,
                                     val db: Database
) : IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url->
                api.getRepos(url).flatMap {repositories->
                    Single.fromCallable {
                        val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")

                        val roomRepos = repositories.map {
                            RoomGithubRepository(it.id ?: "", it.name ?: "", it.watchers ?: 0, it.forks ?: 0,it.language ?: "",roomUser.id)
                        }

                        db.repositoryDao.insert(roomRepos)
                        repositories
                    }
                }
            } ?: Single.error<List<UserRepos>>(RuntimeException("User has no repos url")).subscribeOn(
                Schedulers.io())
        } else {
            Single.fromCallable {
                val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")

                db.repositoryDao.findForUser(roomUser.id).map {
                    UserRepos(it.id, it.name, it.watchers, it.forks, it.language)
                }
            }
        }
    }.subscribeOn(Schedulers.io())

}