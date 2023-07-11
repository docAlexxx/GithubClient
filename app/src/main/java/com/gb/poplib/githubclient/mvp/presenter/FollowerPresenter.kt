package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.Follower
import com.gb.poplib.githubclient.mvp.view.FollowerView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class FollowerPresenter(
    val follower: Follower
) :
    MvpPresenter<FollowerView>() {

    @Inject
    lateinit var router: Router

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(follower)
        follower.avatarUrl?.let { viewState.loadAvatar(it) }
    }
}