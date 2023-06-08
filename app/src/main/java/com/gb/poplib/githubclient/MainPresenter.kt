package com.gb.poplib.githubclient

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun onButtonOneClick() {
        view.setTextButtonOne(model.nextItem(0).toString())
    }

    fun onButtonTwoClick() {
        view.setTextButtonTwo(model.nextItem(1).toString())
    }

    fun onButtonThreeClick() {
        view.setTextButtonThree(model.nextItem(2).toString())
    }
}