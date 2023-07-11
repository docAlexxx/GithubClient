package com.gb.poplib.githubclient.mvp.model.entity.room.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gb.poplib.githubclient.mvp.model.entity.room.RoomFollower
import com.gb.poplib.githubclient.mvp.model.entity.room.RoomGithubRepository


@Dao
interface FollowerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(follower: RoomFollower)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg followers: RoomFollower)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(followers: List<RoomFollower>)

    @Update
    fun update(followers: RoomFollower)

    @Update
    fun update(vararg followers: RoomFollower)

    @Update
    fun update(followers: List<RoomFollower>)

    @Delete
    fun delete(follower: RoomFollower)

    @Delete
    fun delete(vararg followers: RoomFollower)

    @Delete
    fun delete(followers: List<RoomFollower>)

    @Query("SELECT * FROM RoomFollower")
    fun getAll(): List<RoomFollower>

    @Query("SELECT * FROM RoomFollower WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomFollower>
}