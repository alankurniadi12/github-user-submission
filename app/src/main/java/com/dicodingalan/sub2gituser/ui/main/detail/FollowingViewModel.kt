package com.dicodingalan.sub2gituser.ui.main.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicodingalan.sub2gituser.api.ApiConfig
import com.dicodingalan.sub2gituser.datamodel.ResponseFollowersItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {
    private val TAG = FollowingFragment::class.java.simpleName
    private val _responseFollowing = MutableLiveData<ArrayList<ResponseFollowersItem>>()
    val responseFollowing: LiveData<ArrayList<ResponseFollowersItem>> = _responseFollowing

    fun getFollowing(username: String) {
        ApiConfig.getApiService().getFollowing(username)
            .enqueue(object : Callback<ArrayList<ResponseFollowersItem>> {
                override fun onResponse(
                    call: Call<ArrayList<ResponseFollowersItem>>,
                    response: Response<ArrayList<ResponseFollowersItem>>
                ) {
                    _responseFollowing.postValue(response.body())
                }
                override fun onFailure(call: Call<ArrayList<ResponseFollowersItem>>, t: Throwable) {
                    Log.i(TAG, "onFailure: ${t.message}")
                }

            })
    }
}