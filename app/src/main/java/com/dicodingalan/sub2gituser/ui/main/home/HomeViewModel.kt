package com.dicodingalan.sub2gituser.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicodingalan.sub2gituser.helper.SettingPreferences

class HomeViewModel(private val pref: SettingPreferences): ViewModel() {

    fun getThemeSetting(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }
}