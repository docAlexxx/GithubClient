package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.GitHubUserRepo
import com.gb.poplib.githubclient.mvp.model.GithubUser
import com.gb.poplib.githubclient.mvp.presenter.list.UserListPresenter
import com.gb.poplib.githubclient.mvp.view.UserView
import com.gb.poplib.githubclient.mvp.view.list.UserItemView
import com.gb.poplib.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import moxy.MvpPresenter

class UserPresenter(val usersRepo: GitHubUserRepo, val router: Router, val screens: Screens) :
    MvpPresenter<UserView>() {
    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        fun fromIterable(): Observable<GithubUser> {
            return Observable.fromIterable(mutableListOf<GithubUser>())
        }

        override fun bindView(view: UserItemView) {
            Observable.just(users[view.index])
                .map { it.login }
                .subscribe(
                    { log ->
                        view.setLogin(log)
                        println("onNext: $log")
                    }
                )
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
        Observable.fromIterable(usersRepo.getUsers())
            .subscribe(
                { user ->
                    usersListPresenter.users.add(user)
                    println("onNextadd: $user")
                }
            )
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}