package com.gb.poplib.githubclient.mvp.model.entity.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gb.poplib.githubclient.mvp.model.entity.room.Dao.FollowerDao
import com.gb.poplib.githubclient.mvp.model.entity.room.Dao.RepositoryDao
import com.gb.poplib.githubclient.mvp.model.entity.room.Dao.UserDao


@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class, RoomFollower::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val followerDao: FollowerDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null

        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
          //          .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
