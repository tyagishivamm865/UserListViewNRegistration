package com.apps.userlistviewnregistration.Dao

import androidx.room.Dao
import androidx.room.Insert
import com.apps.userlistviewnregistration.model.UserAdress
import com.apps.userlistviewnregistration.model.UserInfo
import com.apps.userlistviewnregistration.model.UserRegistration

@Dao
interface UserRegistrationDao {

    @Insert
    suspend fun insertUserRegistration(userRegistration: UserRegistration)

}

@Dao
interface UserInfoDao {
    @Insert
    suspend fun insertUserInfo(userInfo: UserInfo)
}

@Dao
interface UserUserAddressDao {
    @Insert
    suspend fun insertUserAddress(userAddress: UserAdress)
}