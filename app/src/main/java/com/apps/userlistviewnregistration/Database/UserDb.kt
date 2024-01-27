package com.apps.userlistviewnregistration.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apps.userlistviewnregistration.Dao.UserInfoDao
import com.apps.userlistviewnregistration.Dao.UserRegistrationDao
import com.apps.userlistviewnregistration.Dao.UserUserAddressDao
import com.apps.userlistviewnregistration.model.UserAdress
import com.apps.userlistviewnregistration.model.UserInfo
import com.apps.userlistviewnregistration.model.UserRegistration

@Database(entities = [UserRegistration::class,UserInfo::class,UserAdress::class], version = 1, exportSchema = false)
abstract class UserDb : RoomDatabase() {
    abstract fun userRegDao(): UserRegistrationDao
    abstract fun userInfoDao(): UserInfoDao
    abstract fun userAddDao(): UserUserAddressDao

    companion object {

        private var INSTANCE: UserDb? = null

        fun getDatabase(context: Context): UserDb {
           if(INSTANCE == null) {
               synchronized(this) {
                   INSTANCE = Room.databaseBuilder(
                       context,
                       UserDb::class.java,
                       "user_database"
                   ).build()
               }
           }
            return INSTANCE!!
        }
    }
}
