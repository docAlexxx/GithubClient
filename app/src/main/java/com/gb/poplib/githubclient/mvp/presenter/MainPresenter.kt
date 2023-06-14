package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.view.MainView
import com.gb.poplib.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: Screens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.usersList())
    }

    fun backClicked() {
        router.exit()
    }
}

