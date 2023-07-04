package com.gb.poplib.githubclient.di.modules

import androidx.room.Room
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.repo.cashe.IRepoCashe
import com.gb.poplib.githubclient.mvp.model.repo.cashe.IUserCashe
import com.gb.poplib.githubclient.mvp.model.repo.cashe.RepoCashe
import com.gb.poplib.githubclient.mvp.model.repo.cashe.UserCashe
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun usersCache(database: Database): IUserCashe = UserCashe(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IRepoCashe {
        return RepoCashe(database)
    }

    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .build()
}