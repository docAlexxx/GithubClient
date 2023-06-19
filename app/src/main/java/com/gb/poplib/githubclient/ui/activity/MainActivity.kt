package com.gb.poplib.githubclient.ui.activity

import android.os.Bundle
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.R
import com.gb.poplib.githubclient.databinding.ActivityMainBinding
import com.gb.poplib.githubclient.mvp.presenter.MainPresenter
import com.gb.poplib.githubclient.mvp.view.MainView
import com.gb.poplib.githubclient.navigation.ToolScreens
import com.gb.poplib.githubclient.rxjava.Operators
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private var vb: ActivityMainBinding?=null
    private val presenter by moxyPresenter { MainPresenter(App.instance.router, ToolScreens()) }
    val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        //Creation().exec()
        Operators().exec()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}