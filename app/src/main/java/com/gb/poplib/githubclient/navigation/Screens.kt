package com.gb.poplib.githubclient.navigation

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.github.terrakok.cicerone.Screen

interface Screens {
    fun usersList(): Screen
    fun userItem(user: GithubUser): Screen
    fun repoItem(repo: UserRepos): Screen
}