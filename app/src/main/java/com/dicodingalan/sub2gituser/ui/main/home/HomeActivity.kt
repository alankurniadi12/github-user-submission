package com.dicodingalan.sub2gituser.ui.main.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dicodingalan.sub2gituser.databinding.ActivityHomeBinding
import com.dicodingalan.sub2gituser.helper.SettingPreferences
import com.dicodingalan.sub2gituser.ui.main.search.MainActivity
import com.dicodingalan.sub2gituser.ui.main.favorite.FavoriteActivity
import com.dicodingalan.sub2gituser.ui.main.setting.SettingActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        homeViewModel = HomeViewModel(pref)

        darkModeCheck()

        binding.apply {
            imgSearch.setOnClickListener {
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
            }

            imgFavorite.setOnClickListener {
                startActivity(Intent(this@HomeActivity, FavoriteActivity::class.java))
            }

            imgSetting.setOnClickListener {
                startActivity(Intent(this@HomeActivity, SettingActivity::class.java))
            }
        }

        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()
        darkModeCheck()
    }

    override fun onResume() {
        super.onResume()
        darkModeCheck()
    }

    private fun darkModeCheck() {
        homeViewModel.getThemeSetting().observe(this, {
                isDarkActive: Boolean ->
            if (isDarkActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })
    }
}