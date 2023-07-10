package com.gb.poplib.githubclient.di.modules

import androidx.room.Room
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .build()
}