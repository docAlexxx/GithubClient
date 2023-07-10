package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentRepoBinding
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.mvp.presenter.RepoItemPresenter
import com.gb.poplib.githubclient.mvp.view.RepoDetailsView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoItemFragment : MvpAppCompatFragment(), RepoDetailsView, BackButtonListener {
    private var _binding: FragmentRepoBinding? = null
    private val binding
        get() = _binding!!

    val presenter: RepoItemPresenter by moxyPresenter {
        val repo = arguments?.getParcelable(REPO) as UserRepos?
        RepoItemPresenter(repo!!).apply {
            App.instance.repoSubcomponent?.inject(this)
        }
    }

    companion object {
        const val REPO = "REPO"
        fun newInstance(repo: UserRepos) = RepoItemFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO, repo)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepoBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun init(repo: UserRepos) {
        with(binding!!) {
            repoTv.text = repo.name
            languageTv.text = repo.language
            watchersTv.text = repo.watchers.toString()
            forksTv.text = repo.forks.toString()
        }
    }


}