package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.poplib.githubclient.mvp.presenter.list.RepoListPresenter
import com.gb.poplib.githubclient.mvp.view.RepoView
import com.gb.poplib.githubclient.mvp.view.list.RepoItemView
import com.gb.poplib.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserItemPresenter(
    val uiScheduler: Scheduler,
    val usersRepo: IGithubUsersRepo,
    val router: Router,
    val screens: Screens,
    val user: GithubUser
) :
    MvpPresenter<RepoView>() {
    private var disposable: Disposable? = null

    class ReposListPresenter : RepoListPresenter {
        val repos = mutableListOf<UserRepos>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.index]
            repo.name.let {
                view.setRepoName(it)
            }

        }

        override fun getCount() = repos.size
    }

    val reposListPresenter = ReposListPresenter()

        override fun onFirstViewAttach() {
            super.onFirstViewAttach()
            viewState.init(user)
            loadData()

            reposListPresenter.itemClickListener = { itemView ->
                val repo = reposListPresenter.repos[itemView.index]
                router.navigateTo(screens.repoItem(repo))
            }

        }

    fun loadData() {
        disposable = user.reposUrl?.let {
            usersRepo.getRepos(it)
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    reposListPresenter.repos.clear()
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                })
        }
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