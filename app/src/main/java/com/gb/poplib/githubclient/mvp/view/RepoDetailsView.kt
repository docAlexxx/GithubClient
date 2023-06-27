package com.gb.poplib.githubclient.mvp.view

import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoDetailsView : MvpView {
    fun init(repo: UserRepos)
}