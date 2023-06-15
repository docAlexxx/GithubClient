package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.view.MainView
import com.gb.poplib.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserItemPresenter(
    private val router: Router, val screens: Screens
) : MvpPresenter<MainView>() {

    fun backPressed(): Boolean {
        router.replaceScreen(screens.usersList())
        return true
    }
}