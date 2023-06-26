package com.gb.poplib.githubclient.navigation

import com.gb.poplib.githubclient.ui.fragment.UserItemFragment
import com.gb.poplib.githubclient.ui.fragment.UsersListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ToolScreens : Screens {
    override fun usersList() = FragmentScreen { UsersListFragment.newInstance() }
    override fun userItem(name: String) = FragmentScreen { UserItemFragment.newInstance(name) }

}