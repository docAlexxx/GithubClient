package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentFollowerBinding
import com.gb.poplib.githubclient.databinding.FragmentRepoBinding
import com.gb.poplib.githubclient.mvp.model.entity.Follower
import com.gb.poplib.githubclient.mvp.model.entity.UserRepos
import com.gb.poplib.githubclient.mvp.presenter.FollowerPresenter
import com.gb.poplib.githubclient.mvp.presenter.RepoItemPresenter
import com.gb.poplib.githubclient.mvp.view.FollowerView
import com.gb.poplib.githubclient.mvp.view.IImageLoader
import com.gb.poplib.githubclient.mvp.view.RepoDetailsView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import com.gb.poplib.githubclient.ui.image.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class FollowerFragment(val imageLoader: IImageLoader<ImageView>): MvpAppCompatFragment(), FollowerView, BackButtonListener {
    private var _binding: FragmentFollowerBinding? = null
    private val binding
        get() = _binding!!

     val presenter: FollowerPresenter by moxyPresenter {
        val follower = arguments?.getParcelable(FollowerFragment.FOLLOWER) as Follower?
        FollowerPresenter(follower!!).apply {
            App.instance.followerSubcomponent?.inject(this)
        }
    }

    companion object {
        const val FOLLOWER = "FOLLOWER"
        fun newInstance(follower: Follower) = FollowerFragment(GlideImageLoader()).apply {
            arguments = Bundle().apply {
                putParcelable(FOLLOWER, follower)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentFollowerBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun init(follower: Follower) {
        with(binding!!) {
            followerNameTv.text = follower.login
            followerIdTv.text = follower.id
          }
    }

    override fun loadAvatar(url: String) {
        imageLoader.loadInto(url, binding!!.followerAvatarIv)
    }

}