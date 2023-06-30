package com.gb.poplib.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepos(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val watchers: Int,
    @Expose val forks: Int,
    @Expose val language: String
) : Parcelable