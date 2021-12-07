package com.dicodingalan.sub2gituser.ui.main.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicodingalan.sub2gituser.databinding.ItemUserBinding
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val list = ArrayList<ResponseDetailUser>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setListUser(listUsers: List<ResponseDetailUser>) {
        list.clear()
        list.addAll(listUsers)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseDetailUser) {

            binding.apply {
                Glide.with(itemView)
                    .load(item.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(imgUser)
                tvUser.text = item.login
            }

            binding.root.setOnClickListener {
                onItemClickCallback?.onItemCLicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemCLicked(item: ResponseDetailUser)
    }
}