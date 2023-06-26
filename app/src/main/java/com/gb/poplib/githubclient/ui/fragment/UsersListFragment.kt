package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentUsersListBinding
import com.gb.poplib.githubclient.mvp.model.api.ApiHolder
import com.gb.poplib.githubclient.mvp.model.entity.GitHubUserRepo
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.gb.poplib.githubclient.mvp.presenter.UserPresenter
import com.gb.poplib.githubclient.mvp.view.UserView
import com.gb.poplib.githubclient.navigation.ToolScreens
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import com.gb.poplib.githubclient.ui.adapter.UserAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersListFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private var _binding: FragmentUsersListBinding? = null
    private val binding
        get() = _binding!!

    var adapter: UserAdapter? = null

    val presenter: UserPresenter by moxyPresenter {
        UserPresenter(AndroidSchedulers.mainThread(),RetrofitGithubUsersRepo(ApiHolder.api), App.instance.router, App.instance.screens)
    }

    companion object {
        fun newInstance() = UsersListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersListBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.usersRv?.layoutManager = LinearLayoutManager(context)
        adapter = UserAdapter(presenter.usersListPresenter)
        binding.usersRv?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}