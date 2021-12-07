package com.dicodingalan.sub2gituser.ui.main.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicodingalan.sub2gituser.databinding.ActivityMainBinding
import com.dicodingalan.sub2gituser.datamodel.ItemsItem
import com.dicodingalan.sub2gituser.ui.main.OnItemClickCallback
import com.dicodingalan.sub2gituser.ui.main.UserAdapter
import com.dicodingalan.sub2gituser.ui.main.detail.DetailActivity
import com.dicodingalan.sub2gituser.ui.main.favorite.FavoriteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this)

        adapter = UserAdapter()

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            imgSearch.setOnClickListener {
                showLoading(true)
                val query = binding.edtQuery.text.toString()
                if (query.isEmpty()) return@setOnClickListener
                viewModel.setSearchUser(query)
            }
        }

        viewModel.responseUserSearch.observe(this, {
            showLoading(false)
            adapter.setListUser(it)
        })

        viewModel.responseSearchUserNotFound.observe(this, {
            if (it.totalCount == 0) {
                showLoading(false)
                Toast.makeText(this, "username Tidak ditemukan", Toast.LENGTH_LONG).show()
            }
        })

        adapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(item: ItemsItem) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.NAME_KEY, item.login)
                    it.putExtra(DetailActivity.EXTRA_KEY, item.id)
                    startActivity(it)
                }
            }
        })

        binding.btnGofav.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

    }


    private fun obtainViewModel(
        activity: MainActivity
    ): MainViewModel {
        val factory = ViewModelFactory.getInstance()
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    private fun showLoading(state: Boolean) {
        binding.progressCircular.visibility = if (state) View.VISIBLE else View.GONE
    }
}