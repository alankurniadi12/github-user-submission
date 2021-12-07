package com.dicodingalan.sub2gituser.api

import com.dicodingalan.sub2gituser.BuildConfig
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser
import com.dicodingalan.sub2gituser.datamodel.ResponseFollowersItem
import com.dicodingalan.sub2gituser.datamodel.ResponseSearchUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.APP_KEY}")
    fun searchUser(
        @Query("q") query: String
    ): Call<ResponseSearchUser>

    @GET("/users/{username}")
    @Headers("Authorization: token ${BuildConfig.APP_KEY}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<ResponseDetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.APP_KEY}")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<ResponseFollowersItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.APP_KEY}")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<ResponseFollowersItem>>
}