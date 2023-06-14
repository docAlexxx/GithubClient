package com.gb.poplib.githubclient.navigation

import com.gb.poplib.githubclient.ui.fragment.UserFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ToolScreens:Screens {
    override fun usersList() = FragmentScreen { UserFragment.newInstance() }
}