package com.dicodingalan.sub2gituser.ui.main.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicodingalan.sub2gituser.api.ApiConfig
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser
import com.dicodingalan.sub2gituser.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): ViewModel() {
    private val TAG = DetailViewModel::class.java.simpleName
    private val _resposeDetailUser = MutableLiveData<ResponseDetailUser>()
    val responseDetailUser: LiveData<ResponseDetailUser> = _resposeDetailUser

    private val mUserRepo: UserRepository = UserRepository(application)

    fun getDetailUser(username: String) {
        ApiConfig.getApiService().getDetailUser(username)
            .enqueue(object : Callback<ResponseDetailUser> {
                override fun onResponse(
                    call: Call<ResponseDetailUser>,
                    response: Response<ResponseDetailUser>
                ) {
                    Log.i(TAG, "onResponse: $response")
                    _resposeDetailUser.postValue(response.body())
                }
                override fun onFailure(call: Call<ResponseDetailUser>, t: Throwable) {
                    Log.i(TAG, "onFailure getDetailUser: ${t.message}")
                }
            })
    }

    fun insert(responseDetailUser: ResponseDetailUser) {
        mUserRepo.insert(responseDetailUser)
    }

    fun delete(responseDetailUser: ResponseDetailUser) {
        mUserRepo.delete(responseDetailUser)
    }

    fun getFavoriteById(id: Int): LiveData<List<ResponseDetailUser>> {
        return mUserRepo.getUserFavoriteById(id)
    }
}