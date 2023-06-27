package com.gb.poplib.githubclient.mvp.view

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoView : MvpView {
    fun init(user: GithubUser)
    fun updateList()
}