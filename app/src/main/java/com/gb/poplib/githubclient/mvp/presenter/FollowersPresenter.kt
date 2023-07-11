package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.di.follower.modules.IFollowerScopeContainer
import com.gb.poplib.githubclient.di.repo.modules.IRepoScopeContainer
import com.gb.poplib.githubclient.mvp.model.entity.Follower
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.repo.IFollowersRepo
import com.gb.poplib.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.gb.poplib.githubclient.mvp.presenter.list.FollowerListPresenter
import com.gb.poplib.githubclient.mvp.presenter.list.RepoListPresenter
import com.gb.poplib.githubclient.mvp.view.FollowersView
import com.gb.poplib.githubclient.mvp.view.RepoView
import com.gb.poplib.githubclient.mvp.view.list.FollowerItemView
import com.gb.poplib.githubclient.mvp.view.list.RepoItemView
import com.gb.poplib.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import javax.inject.Inject

class FollowersPresenter(val user: GithubUser):
    MvpPresenter<FollowersView>() {
    private var disposable: Disposable? = null

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: Screens
    @Inject
    lateinit var uiScheduler: Scheduler
    @Inject
    lateinit var followersRepo: IFollowersRepo
    @Inject
    lateinit var database: Database
    @Inject
    lateinit var followerScopeContainer: IFollowerScopeContainer

    class FollowersListPresenter : FollowerListPresenter {
        val followers = mutableListOf<Follower>()

        override var itemClickListener: ((FollowerItemView) -> Unit)? = null

        override fun bindView(view: FollowerItemView) {
            val follower = followers[view.index]
            follower.login.let {
                view.setFollowerName(it)
            }
        }

        override fun getCount() = followers.size
    }

    val followersListPresenter = FollowersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(user)
        loadData()

        followersListPresenter.itemClickListener = { itemView ->
            val follower = followersListPresenter.followers[itemView.index]
            router.navigateTo(screens.followerItem(follower))
        }

    }

    fun loadData() {
     disposable = user.followersUrl?.let {
                   followersRepo.getFollowers(user)
                .observeOn(uiScheduler)
                .subscribe({ followers ->
                    followersListPresenter.followers.clear()
                    followersListPresenter.followers.addAll(followers)
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
        followerScopeContainer.releaseFollowerScope()
        super.onDestroy()
        disposable?.dispose()
    }

}