package com.gb.poplib.githubclient.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.poplib.githubclient.databinding.ActivityMainBinding
import com.gb.poplib.githubclient.mvp.model.GitHubUserRepo
import com.gb.poplib.githubclient.mvp.presenter.MainPresenter
import com.gb.poplib.githubclient.mvp.view.MainView
import com.gb.poplib.githubclient.ui.adapter.UserAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var vb: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(GitHubUserRepo()) }
    private var adapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

    }

    override fun init() {
        vb?.usersRv?.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(presenter.usersListPresenter)
        vb?.usersRv?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


}