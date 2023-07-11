package com.gb.poplib.githubclient.di.follower

import com.gb.poplib.githubclient.di.follower.modules.FollowerModule
import com.gb.poplib.githubclient.di.repo.FollowerScope
import com.gb.poplib.githubclient.mvp.presenter.FollowerPresenter
import com.gb.poplib.githubclient.mvp.presenter.FollowersPresenter
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