package com.dicodingalan.sub2gituser.ui.main.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicodingalan.sub2gituser.R
import com.dicodingalan.sub2gituser.databinding.ActivityDetailBinding
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var menu: Menu
    private lateinit var detailViewModel: DetailViewModel
    private val responseDetailUser = ResponseDetailUser()
    private var isFavorite = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = obtainViewModel(this@DetailActivity)


        val username = intent.getStringExtra(NAME_KEY)
        if (username != null) {
            showLoading(true)
            detailViewModel.getDetailUser(username)
        }
        detailViewModel.responseDetailUser.observe(this, {
            setDataViewDetail(it)
        })


        val bundle = Bundle()
        bundle.putString(NAME_KEY, username)


        responseDetailUser.id = intent.getIntExtra(EXTRA_KEY, 0)
        detailViewModel.getFavoriteById(responseDetailUser.id!!)
            .observe(this, {
                if (it.isNotEmpty()) isFavorite = true
            })


        val sectionPagerAdapter = SectionPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu_detail, menu)

        if (isFavorite) {
            menu.findItem(R.id.favorite).icon = getDrawable(R.drawable.ic_baseline_favorite_24)
        } else {
            menu.findItem(R.id.favorite).icon = getDrawable(R.drawable.ic_baseline_favorite_border_24)
        }

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            R.id.favorite -> {

                if (isFavorite) {
                    detailViewModel.delete(responseDetailUser)
                    menu.findItem(R.id.favorite).icon = getDrawable(R.drawable.ic_baseline_favorite_border_24)
                    isFavorite = false
                }else{
                    detailViewModel.insert(responseDetailUser)
                    menu.findItem(R.id.favorite).icon = getDrawable(R.drawable.ic_baseline_favorite_24)
                    isFavorite = true
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setDataViewDetail(it: ResponseDetailUser?) {
        if (it != null) {
            showLoading(false)
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(it.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(imgProfileUser)
                tvName.text = it.name
                tvUsername.text = it.login
                tvRepo.text = getString(R.string._repository, it.publicRepos.toString())
                tvFollowers.text = getString(R.string._followers, it.followers.toString())
                tvFollowing.text = getString(R.string._following, it.following.toString())
                tvLocation.text = it.location
                tvCompany.text = it.company
            }

            responseDetailUser.id= it.id
            responseDetailUser.name = it.name
            responseDetailUser.login = it.login
            responseDetailUser.avatarUrl = it.avatarUrl
        }else {
            showLoading(false)
            Toast.makeText(this, "Gagal Mendapatkan Data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressCircular.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = DetailViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailViewModel::class.java]
    }

    companion object {
        const val EXTRA_KEY = "extra_KEY"
        const val NAME_KEY = "name_key"
        private val TAB_TITLE = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }
}