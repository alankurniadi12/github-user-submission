package com.dicodingalan.sub2gituser.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "login")
    var login: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "publicRepos")
    var publicRepos: Int? = null,

    @ColumnInfo(name = "followers")
    var followers: Int? = null,

    @ColumnInfo(name = "following")
    var following: Int? = null,

    @ColumnInfo(name = "company")
    var company: String? = null,

    @ColumnInfo(name = "location")
    var location: String? = null,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null
): Parcelable