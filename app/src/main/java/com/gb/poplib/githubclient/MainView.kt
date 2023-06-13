package com.gb.poplib.githubclient

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun setTextButtonOne(counter: String)
    fun setTextButtonTwo(counter: String)
    fun setTextButtonThree(counter: String)

}