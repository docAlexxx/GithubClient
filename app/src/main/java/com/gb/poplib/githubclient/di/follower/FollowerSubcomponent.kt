package com.gb.poplib.githubclient.di.follower

import com.gb.poplib.githubclient.di.follower.modules.FollowerModule
import com.gb.poplib.githubclient.di.modules.GlideModule
import com.gb.poplib.githubclient.di.repo.FollowerScope
import com.gb.poplib.githubclient.mvp.presenter.FollowerPresenter
import com.gb.poplib.githubclient.mvp.presenter.FollowersPresenter
import com.gb.poplib.githubclient.mvp.presenter.RepoItemPresenter
import com.gb.poplib.githubclient.mvp.presenter.UserItemPresenter
import com.gb.poplib.githubclient.ui.fragment.FollowerFragment
import dagger.Subcomponent


@FollowerScope
@Subcomponent(
    modules = [
        FollowerModule::class

    ]
)

interface FollowerSubcomponent {
    fun inject(followersPresenter: FollowersPresenter)
    fun inject(followerPresenter: FollowerPresenter)
}