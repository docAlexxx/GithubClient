package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentUserBinding
import com.gb.poplib.githubclient.mvp.presenter.UserItemPresenter
import com.gb.poplib.githubclient.mvp.view.MainView
import com.gb.poplib.githubclient.navigation.ToolScreens
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import com.gb.poplib.githubclient.ui.adapter.UserAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserItemFragment(val name: String) : MvpAppCompatFragment(), MainView, BackButtonListener {
    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    var adapter: UserAdapter? = null

    val presenter: UserItemPresenter by moxyPresenter {
        UserItemPresenter(App.instance.router, ToolScreens())
    }

    companion object {
        fun newInstance(text: String) = UserItemFragment(text)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(_binding!!) {
            userTv.text = name

        }
    }

    override fun backPressed() = presenter.backPressed()
}