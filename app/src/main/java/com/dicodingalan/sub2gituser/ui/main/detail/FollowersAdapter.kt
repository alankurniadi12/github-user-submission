package com.dicodingalan.sub2gituser.ui.main.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicodingalan.sub2gituser.databinding.ItemUserBinding
import com.dicodingalan.sub2gituser.datamodel.ResponseFollowersItem

class FollowersAdapter : RecyclerView.Adapter<FollowersAdapter.UserViewHolder>() {

    private val list = ArrayList<ResponseFollowersItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setListUser(listFollowers: ArrayList<ResponseFollowersItem>) {
        list.clear()
        list.addAll(listFollowers)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseFollowersItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(item)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(item.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(imgUser)
                tvUser.text = item.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

interface OnItemClickCallback {
    fun onItemClicked(item: ResponseFollowersItem)
}