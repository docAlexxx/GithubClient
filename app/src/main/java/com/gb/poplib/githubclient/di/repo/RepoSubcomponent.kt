package com.gb.poplib.githubclient.di.repo

import com.gb.poplib.githubclient.di.repo.modules.RepoModule
import com.gb.poplib.githubclient.mvp.presenter.RepoItemPresenter
import com.gb.poplib.githubclient.mvp.presenter.UserItemPresenter
import dagger.Subcomponent

@RepoScope
@Subcomponent(
    modules = [
        RepoModule::class
    ]
)

interface RepoSubcomponent {
    fun inject(userItemPresenter: UserItemPresenter)
    fun inject(repoItemPresenter: RepoItemPresenter)
}