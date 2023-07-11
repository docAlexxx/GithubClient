package com.gb.poplib.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.poplib.githubclient.START_INDEX
import com.gb.poplib.githubclient.databinding.FollowerItemBinding
import com.gb.poplib.githubclient.databinding.RepoItemBinding
import com.gb.poplib.githubclient.mvp.presenter.list.FollowerListPresenter
import com.gb.poplib.githubclient.mvp.view.list.FollowerItemView
import com.gb.poplib.githubclient.mvp.view.list.RepoItemView

class FollowerAdapter(val presenter: FollowerListPresenter) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowerViewHolder(
            FollowerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: FollowerAdapter.FollowerViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            index = position
        })

    inner class FollowerViewHolder(val vb: FollowerItemBinding) : RecyclerView.ViewHolder(vb.root),
        FollowerItemView {
        override var index = START_INDEX

        override fun setFollowerName(login: String) = with(vb) {
            followerNameTv.text = login
        }

    }

}