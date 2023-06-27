package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.mvp.view.RepoDetailsView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepoItemPresenter(
    val router: Router,
    val repo: UserRepos
) :
    MvpPresenter<RepoDetailsView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(repo)
    }

}