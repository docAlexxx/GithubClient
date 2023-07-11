package com.gb.poplib.githubclient.di.user

import com.gb.poplib.githubclient.di.follower.FollowerSubcomponent
import com.gb.poplib.githubclient.di.repo.RepoSubcomponent
import com.gb.poplib.githubclient.di.user.Modules.UserModule
import com.gb.poplib.githubclient.mvp.presenter.UserPresenter
import com.gb.poplib.githubclient.ui.adapter.FollowerAdapter
import com.gb.poplib.githubclient.ui.adapter.UserAdapter
import com.gb.poplib.githubclient.ui.fragment.FollowerFragment
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(userAdapter: UserAdapter)

    fun repoSubcomponent(): RepoSubcomponent
    fun followerSubcomponent(): FollowerSubcomponent
}