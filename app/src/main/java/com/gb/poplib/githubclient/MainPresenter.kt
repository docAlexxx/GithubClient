package com.gb.poplib.githubclient

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun onButtonOneClick() {
        view.setTextButtonOne(model.nextItem(BUTTON_ONE).toString())
    }

    fun onButtonTwoClick() {
        view.setTextButtonTwo(model.nextItem(BUTTON_TWO).toString())
    }

    fun onButtonThreeClick() {
        view.setTextButtonThree(model.nextItem(BUTTON_THREE).toString())
    }
}