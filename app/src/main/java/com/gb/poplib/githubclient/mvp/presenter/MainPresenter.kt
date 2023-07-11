package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.view.MainView
import com.gb.poplib.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: Screens
    @Inject
   lateinit var database: Database

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.usersList())
    }

    fun backClicked() {
        router.exit()
    }
}

