package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.gb.poplib.githubclient.mvp.presenter.list.RepoListPresenter
import com.gb.poplib.githubclient.mvp.view.RepoView
import com.gb.poplib.githubclient.mvp.view.list.RepoItemView
import com.gb.poplib.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import javax.inject.Inject

class UserItemPresenter(
    val user: GithubUser
) :
    MvpPresenter<RepoView>() {
    private var disposable: Disposable? = null

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: Screens
    @Inject
    lateinit var uiScheduler: Scheduler
    @Inject
    lateinit var usersRepo: IGithubRepositoriesRepo
    @Inject
    lateinit var database: Database


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
            usersRepo.getRepositories(user)
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    reposListPresenter.repos.clear()
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
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