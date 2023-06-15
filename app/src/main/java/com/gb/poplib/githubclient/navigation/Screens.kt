package com.gb.poplib.githubclient.navigation

import com.github.terrakok.cicerone.Screen

interface Screens {
    fun usersList(): Screen
    fun userItem(name: String): Screen
}