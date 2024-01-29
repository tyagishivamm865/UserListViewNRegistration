package com.apps.userlistviewnregistration.Database

import android.content.Context
import androidx.lifecycle.LiveData
import com.apps.userlistviewnregistration.model.CombinedDataEntity
import com.apps.userlistviewnregistration.model.UserAdress
import com.apps.userlistviewnregistration.model.UserInfo
import com.apps.userlistviewnregistration.model.UserRegistration

class UserRepository(context: Context) {

    val userRegDao = UserDb.getDatabase(context).userRegDao()
    val userInfoDao = UserDb.getDatabase(context).userInfoDao()
    val userAddDao = UserDb.getDatabase(context).userAddDao()
    val combineDao = UserDb.getDatabase(context).usercombineDao()
    suspend fun insertUserRegistration(userRegistration: UserRegistration) {
        userRegDao.insertUserRegistration(userRegistration)
    }

    suspend fun insertUserAddress(userAddress: UserAdress) {
        userAddDao.insertUserAddress(userAddress)
    }

    suspend fun insertUserInfo(userInfo: UserInfo) {
        userInfoDao.insertUserInfo(userInfo)
    }

     fun getAllCombinedData(): LiveData<List<CombinedDataEntity>>? {
        return combineDao.getUsersWithEducationAndAddress()
    }

    suspend fun getmixdata(): List<CombinedDataEntity>? {
        return combineDao.getmixdata()
    }

}
