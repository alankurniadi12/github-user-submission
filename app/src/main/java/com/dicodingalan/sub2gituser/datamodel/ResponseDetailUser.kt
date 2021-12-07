package com.dicodingalan.sub2gituser.datamodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class ResponseDetailUser(

	@field:SerializedName("login")
	@ColumnInfo(name = "login")
	var login: String? = null,


	@field:SerializedName("company")
	@ColumnInfo(name = "company")
	var company: String? = null,

	@PrimaryKey(autoGenerate = true)
	@field:SerializedName("id")
	@ColumnInfo(name = "id")
	var id: Int? = null,


	@field:SerializedName("public_repos")
	@ColumnInfo(name = "publicRepos")
	var publicRepos: Int? = null,


	@field:SerializedName("followers")
	@ColumnInfo(name = "followers")
	var followers: Int? = null,


	@field:SerializedName("avatar_url")
	@ColumnInfo(name = "avatarUrl")
	var avatarUrl: String? = null,


	@field:SerializedName("following")
	@ColumnInfo(name = "following")
	var following: Int? = null,


	@field:SerializedName("name")
	@ColumnInfo(name = "name")
	var name: String? = null,


	@field:SerializedName("location")
	@ColumnInfo(name = "location")
	var location: String? = null,
): Parcelable
