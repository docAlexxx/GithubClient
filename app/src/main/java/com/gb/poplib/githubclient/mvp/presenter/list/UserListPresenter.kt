package com.gb.poplib.githubclient.mvp.presenter.list

import com.gb.poplib.githubclient.mvp.view.list.UserItemView

interface UserListPresenter : ListPresenter<UserItemView> {
    fun showRepos(view: UserItemView)
    fun showFollowers(view: UserItemView)
}