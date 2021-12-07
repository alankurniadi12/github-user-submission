package com.dicodingalan.sub2gituser.ui.main.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicodingalan.sub2gituser.databinding.ActivityFavoriteBinding
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser
import com.dicodingalan.sub2gituser.ui.main.detail.DetailActivity

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FavoriteAdapter()

        binding.apply {
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavorite.setHasFixedSize(true)
            rvFavorite.adapter = adapter
        }

        favoriteViewModel = obtainViewModel(this)

        favoriteViewModel.getAllUsersFavorite().observe(this, {
            if (it != null) adapter.setListUser(it)
        })

        adapter.setOnClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemCLicked(item: ResponseDetailUser) {
                Intent(this@FavoriteActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.NAME_KEY, item.login)
                    it.putExtra(DetailActivity.EXTRA_KEY, item.id)
                    startActivity(it)
                }
            }
        })
    }


    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = FavoriteViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }
}