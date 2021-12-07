package com.dicodingalan.sub2gituser.ui.main.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser
import com.dicodingalan.sub2gituser.repository.UserRepository

class FavoriteViewModel(application: Application): ViewModel() {

    private val mUserRepository: UserRepository = UserRepository(application)

    fun getAllUsersFavorite(): LiveData<List<ResponseDetailUser>> = mUserRepository.getAllUsers()
}