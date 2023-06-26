package com.gb.poplib.githubclient.mvp.view.list

interface UserItemView : ItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}