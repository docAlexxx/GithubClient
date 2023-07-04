package com.gb.poplib.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gb.poplib.githubclient.START_INDEX
import com.gb.poplib.githubclient.databinding.UserItemBinding
import com.gb.poplib.githubclient.mvp.presenter.list.UserListPresenter
import com.gb.poplib.githubclient.mvp.view.IImageLoader
import com.gb.poplib.githubclient.mvp.view.list.UserItemView
import javax.inject.Inject

class UserAdapter(val presenter: UserListPresenter) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            index = position
        })

    inner class UserViewHolder(val vb: UserItemBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var index = START_INDEX

        override fun setLogin(login: String) = with(vb) {
            userLoginTv.text = login
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, vb.avatarIv)
        }


    }


}