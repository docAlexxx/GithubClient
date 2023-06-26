package com.gb.poplib.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.poplib.githubclient.START_INDEX
import com.gb.poplib.githubclient.databinding.RepoItemBinding
import com.gb.poplib.githubclient.mvp.presenter.list.RepoListPresenter
import com.gb.poplib.githubclient.mvp.view.list.RepoItemView

class RepoAdapter(val presenter: RepoListPresenter) :
    RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepoViewHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            index = position
        })

    inner class RepoViewHolder(val vb: RepoItemBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override var index = START_INDEX

        override fun setRepoName(login: String) = with(vb) {
            repoNameTv.text = login
        }

    }


}