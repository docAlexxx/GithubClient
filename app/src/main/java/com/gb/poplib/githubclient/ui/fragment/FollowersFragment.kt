package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentFollowersListBinding
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.presenter.FollowersPresenter
import com.gb.poplib.githubclient.mvp.view.FollowersView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import com.gb.poplib.githubclient.ui.adapter.FollowerAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FollowersFragment : MvpAppCompatFragment(), FollowersView, BackButtonListener {
    private var _binding: FragmentFollowersListBinding? = null
    private val binding
        get() = _binding!!

    var adapter: FollowerAdapter? = null

    val presenter: FollowersPresenter by moxyPresenter {
        val user = arguments?.getParcelable(FollowersFragment.USER) as GithubUser?
        FollowersPresenter(user!!).apply {
            App.instance.initFollowerSubcomponent()?.inject(this)
        }
    }

    companion object {
        const val USER = "USER"
        fun newInstance(user: GithubUser) = FollowersFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER, user)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentFollowersListBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init(user: GithubUser) {
        binding.followersRv?.layoutManager = LinearLayoutManager(context)
        adapter = FollowerAdapter(presenter.followersListPresenter)
        binding.followersRv?.adapter = adapter
        binding.userTv.text = user.login + "'s followers:"
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}