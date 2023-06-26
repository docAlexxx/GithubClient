package com.gb.poplib.githubclient.mvp.view

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}