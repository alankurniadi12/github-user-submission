package com.dicodingalan.sub2gituser.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser

@Database(entities = [ResponseDetailUser::class], version = 1)
abstract class UserRoomDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context): UserRoomDatabase {
            if (INSTANCE == null) {
                synchronized(UserRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    UserRoomDatabase::class.java, "dbUsers")
                        .build()
                }
            }
            return INSTANCE as UserRoomDatabase
        }
    }
}