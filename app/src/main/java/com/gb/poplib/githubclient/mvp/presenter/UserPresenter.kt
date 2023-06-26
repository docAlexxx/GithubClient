package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.poplib.githubclient.mvp.presenter.list.UserListPresenter
import com.gb.poplib.githubclient.mvp.view.UserView
import com.gb.poplib.githubclient.mvp.view.list.UserItemView
import com.gb.poplib.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserPresenter(
    val uiScheduler: Scheduler,
    val usersRepo: IGithubUsersRepo,
    val router: Router,
    val screens: Screens
) :
    MvpPresenter<UserView>() {
    private var disposable: Disposable? = null

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.index]
            user.login.let {
                view.setLogin(it)
            }
            user.avatarUrl?.let {
                view.loadAvatar(it)
            }
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val name = usersListPresenter.users[itemView.index].login
            router.replaceScreen(screens.userItem(name))
        }
    }

    fun loadData() {
        disposable = usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}