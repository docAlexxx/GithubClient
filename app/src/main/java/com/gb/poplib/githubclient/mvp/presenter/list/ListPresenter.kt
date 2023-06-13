package com.gb.poplib.githubclient.mvp.presenter.list

import com.gb.poplib.githubclient.mvp.view.list.ItemView

interface ListPresenter<V : ItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}