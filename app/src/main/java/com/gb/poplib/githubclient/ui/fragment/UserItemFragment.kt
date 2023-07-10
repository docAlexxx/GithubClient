package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentUserBinding
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.presenter.UserItemPresenter
import com.gb.poplib.githubclient.mvp.view.RepoView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import com.gb.poplib.githubclient.ui.adapter.RepoAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserItemFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {
    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    var adapter: RepoAdapter? = null

    val presenter: UserItemPresenter by moxyPresenter {
        val user = arguments?.getParcelable(USER) as GithubUser?
        UserItemPresenter(user!!).apply {
            App.instance.initRepoSubcomponent()?.inject(this)
        }
    }

    companion object {
        const val USER = "USER"
        fun newInstance(user: GithubUser) = UserItemFragment().apply {
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
        FragmentUserBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init(user: GithubUser) {
        binding.reposRv?.layoutManager = LinearLayoutManager(context)
        adapter = RepoAdapter(presenter.reposListPresenter)
        binding.reposRv?.adapter = adapter
        binding.userTv.text = user.login + "'s repos:"
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}