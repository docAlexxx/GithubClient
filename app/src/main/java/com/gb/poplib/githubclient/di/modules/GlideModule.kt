package com.gb.poplib.githubclient.di.modules

import android.widget.ImageView
import com.gb.poplib.githubclient.mvp.view.IImageLoader
import com.gb.poplib.githubclient.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GlideModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}