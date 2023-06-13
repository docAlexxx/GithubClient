package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.GitHubUserRepo
import com.gb.poplib.githubclient.mvp.model.GithubUser
import com.gb.poplib.githubclient.mvp.presenter.list.UserListPresenter
import com.gb.poplib.githubclient.mvp.view.MainView
import com.gb.poplib.githubclient.mvp.view.list.UserItemView
import moxy.MvpPresenter

class MainPresenter(val usersRepo: GitHubUserRepo) : MvpPresenter<MainView>() {

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.index]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            // TODO
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}