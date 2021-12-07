package com.dicodingalan.sub2gituser.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(responseDetailUser: ResponseDetailUser)

    @Update
    fun Update(responseDetailUser: ResponseDetailUser)

    @Delete
    fun delete(responseDetailUser: ResponseDetailUser)

    @Query("SELECT * from responsedetailuser")
    fun getAllFavoriteUser(): LiveData<List<ResponseDetailUser>>

    @Query("SELECT * from responsedetailuser WHERE id = :id ")
    fun getFavoriteUserById(id: Int): LiveData<List<ResponseDetailUser>>
}