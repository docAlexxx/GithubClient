package com.gb.poplib.githubclient.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomFollower(
    @PrimaryKey var id: String,
    var login: String,
    var avatarUrl: String,
    var userId: String
    )