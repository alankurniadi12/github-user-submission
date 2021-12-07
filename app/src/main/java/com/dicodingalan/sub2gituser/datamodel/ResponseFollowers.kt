package com.dicodingalan.sub2gituser.datamodel

import com.google.gson.annotations.SerializedName

data class ResponseFollowers(

	@field:SerializedName("ResponseFollowers")
	val responseFollowers: ArrayList<ResponseFollowersItem>
)

data class ResponseFollowersItem(

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,
)
