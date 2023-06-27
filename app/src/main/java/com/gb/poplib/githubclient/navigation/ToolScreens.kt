package com.gb.poplib.githubclient.navigation

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.ui.fragment.RepoItemFragment
import com.gb.poplib.githubclient.ui.fragment.UserItemFragment
import com.gb.poplib.githubclient.ui.fragment.UsersListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ToolScreens : Screens {
    override fun usersList() = FragmentScreen { UsersListFragment.newInstance() }
    override fun userItem(user: GithubUser) = FragmentScreen { UserItemFragment.newInstance(user) }
    override fun repoItem(repo: UserRepos) = FragmentScreen { RepoItemFragment.newInstance(repo) }
}