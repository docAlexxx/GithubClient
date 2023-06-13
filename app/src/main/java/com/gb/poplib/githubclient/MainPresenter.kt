package com.gb.poplib.githubclient

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