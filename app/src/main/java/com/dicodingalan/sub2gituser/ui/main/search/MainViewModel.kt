package com.dicodingalan.sub2gituser.ui.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicodingalan.sub2gituser.api.ApiConfig
import com.dicodingalan.sub2gituser.datamodel.ItemsItem
import com.dicodingalan.sub2gituser.datamodel.ResponseSearchUser
import com.dicodingalan.sub2gituser.helper.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName
    private val _responseUserSearch = MutableLiveData<ArrayList<ItemsItem>>()
    var responseUserSearch: LiveData<ArrayList<ItemsItem>> = _responseUserSearch

    private val _responseSearchNotFound = MutableLiveData<ResponseSearchUser>()
    val responseSearchUserNotFound: LiveData<ResponseSearchUser> = _responseSearchNotFound


    fun setSearchUser(query: String) {
        ApiConfig.getApiService().searchUser(query)
            .enqueue(object : Callback<ResponseSearchUser> {
                override fun onResponse(
                    call: Call<ResponseSearchUser>,
                    response: Response<ResponseSearchUser>
                ) {
                    if (response.body()?.totalCount != 0) {
                        _responseUserSearch.postValue(response.body()?.items)
                    } else {
                        _responseSearchNotFound.postValue(response.body())
                    }

                }
                override fun onFailure(call: Call<ResponseSearchUser>, t: Throwable) {
                    Log.i(TAG, "onFailure setSearchUser: ${t.message}")
                }

            })
    }
}