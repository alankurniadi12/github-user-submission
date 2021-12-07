package com.dicodingalan.sub2gituser.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicodingalan.sub2gituser.database.UserDao
import com.dicodingalan.sub2gituser.database.UserRoomDatabase
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository(application: Application) {
    private val mUserDao: UserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserRoomDatabase.getDatabase(application)
        mUserDao = db.userDao()
    }

    fun insert(responseDetailUser: ResponseDetailUser) {
        executorService.execute { mUserDao.insert(responseDetailUser) }
    }

    fun update(responseDetailUser: ResponseDetailUser) {
        executorService.execute { mUserDao.Update(responseDetailUser) }
    }

    fun delete(responseDetailUser: ResponseDetailUser) {
        executorService.execute { mUserDao.delete(responseDetailUser) }
    }

    fun getUserFavoriteById(id: Int): LiveData<List<ResponseDetailUser>> = mUserDao.getFavoriteUserById(id)

    fun getAllUsers(): LiveData<List<ResponseDetailUser>> = mUserDao.getAllFavoriteUser()
}