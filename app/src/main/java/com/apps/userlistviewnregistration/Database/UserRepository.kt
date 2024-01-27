package com.apps.userlistviewnregistration.Database

import android.content.Context
import com.apps.userlistviewnregistration.model.UserAdress
import com.apps.userlistviewnregistration.model.UserInfo
import com.apps.userlistviewnregistration.model.UserRegistration

class UserRepository(context: Context) {

    val userRegDao = UserDb.getDatabase(context).userRegDao()
    val userInfoDao = UserDb.getDatabase(context).userInfoDao()
    val userAddDao = UserDb.getDatabase(context).userAddDao()
    suspend fun insertUserRegistration(userRegistration: UserRegistration) {
        userRegDao.insertUserRegistration(userRegistration)
    }

    suspend fun insertUserAddress(userAddress: UserAdress) {
        userAddDao.insertUserAddress(userAddress)
    }

    suspend fun insertUserInfo(userInfo: UserInfo) {
        userInfoDao.insertUserInfo(userInfo)
    }

}
