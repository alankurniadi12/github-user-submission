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

class FollowersViewModel: ViewModel() {

    private val TAG = FollowersViewModel::class.java.simpleName
    private val _followersRespose = MutableLiveData<ArrayList<ResponseFollowersItem>>()
    val followersResponse: LiveData<ArrayList<ResponseFollowersItem>> = _followersRespose

    fun getFollowers(username: String) {
        Log.i(TAG, "getFollowers: $username")
        ApiConfig.getApiService().getFollowers(username)
            .enqueue(object : Callback<ArrayList<ResponseFollowersItem>> {
                override fun onResponse(
                    call: Call<ArrayList<ResponseFollowersItem>>,
                    response: Response<ArrayList<ResponseFollowersItem>>
                ) {
                    Log.i(TAG, "onResponse:$response")
                    _followersRespose.postValue(response.body())
                }

                override fun onFailure(call: Call<ArrayList<ResponseFollowersItem>>, t: Throwable) {
                    Log.i(TAG, "onFailure: ${t.message}")
                }

            })
    }
}