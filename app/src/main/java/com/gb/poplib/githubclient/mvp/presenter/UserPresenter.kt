package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.di.user.Modules.IUserScopeContainer
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.poplib.githubclient.mvp.presenter.list.UserListPresenter
import com.gb.poplib.githubclient.mvp.view.UserView
import com.gb.poplib.githubclient.mvp.view.list.UserItemView
import com.gb.poplib.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter() :
    MvpPresenter<UserView>() {
    private var disposable: Disposable? = null

    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: Screens
    @Inject
    lateinit var uiScheduler: Scheduler
    @Inject
    lateinit var userScopeContainer: IUserScopeContainer
    @Inject
    lateinit var database: Database

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()
        private val reposObservers = mutableListOf<(UserItemView) -> Unit>()
        private val followersObservers = mutableListOf<(UserItemView) -> Unit>()

        override fun showRepos(view: UserItemView) {
            reposObservers.forEach { observer ->
                observer.invoke(view)
            }
        }

        override fun showFollowers(view: UserItemView) {
            followersObservers.forEach { observer ->
                observer.invoke(view)
            }
        }

        fun addReposObserver(observer: (UserItemView) -> Unit) {
            reposObservers.add(observer)
        }

        fun addFollowersObserver(observer: (UserItemView) -> Unit) {
            followersObservers.add(observer)
        }

        fun removeReposObserver(observer: (UserItemView) -> Unit) {
            reposObservers.remove(observer)
        }

        fun removeFollowersObserver(observer: (UserItemView) -> Unit) {
            followersObservers.remove(observer)
        }

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
    private lateinit var reposObserver: (UserItemView) -> Unit
    private lateinit var followersObserver: (UserItemView) -> Unit

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        reposObserver = { userItemView ->
            val user = usersListPresenter.users[userItemView.index]
            router.navigateTo(screens.userItem(user))
        }

        followersObserver = { userItemView ->
            val user = usersListPresenter.users[userItemView.index]
            router.navigateTo(screens.followers(user))
        }

        usersListPresenter.addReposObserver(reposObserver)
        usersListPresenter.addFollowersObserver(followersObserver)
    }

    fun loadData() {
        disposable = usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        userScopeContainer.releaseUserScope()
        super.onDestroy()
        disposable?.dispose()
        usersListPresenter.removeReposObserver(reposObserver)
        usersListPresenter.removeFollowersObserver(followersObserver)
    }
}