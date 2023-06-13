package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.BUTTON_ONE
import com.gb.poplib.githubclient.BUTTON_THREE
import com.gb.poplib.githubclient.BUTTON_TWO
import com.gb.poplib.githubclient.mvp.view.MainView
import com.gb.poplib.githubclient.ui.CountersModel
import moxy.MvpPresenter

class MainPresenter(val model: CountersModel):MvpPresenter<MainView>() {


    fun onButtonOneClick() {
        viewState.setTextButtonOne(model.nextItem(BUTTON_ONE).toString())
    }

    fun onButtonTwoClick() {
        viewState.setTextButtonTwo(model.nextItem(BUTTON_TWO).toString())
    }

    fun onButtonThreeClick() {
        viewState.setTextButtonThree(model.nextItem(BUTTON_THREE).toString())
    }
}