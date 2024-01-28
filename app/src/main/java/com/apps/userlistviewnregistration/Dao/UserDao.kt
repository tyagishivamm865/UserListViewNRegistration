package com.apps.userlistviewnregistration.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.apps.userlistviewnregistration.model.CombinedDataEntity
import com.apps.userlistviewnregistration.model.UserAdress
import com.apps.userlistviewnregistration.model.UserInfo
import com.apps.userlistviewnregistration.model.UserRegistration

@Dao
interface UserRegistrationDao {

    @Insert
    suspend fun insertUserRegistration(userRegistration: UserRegistration)

    @Query("SELECT * FROM user_registration")
    suspend fun getAllfromregistration(): List<UserRegistration>

}

@Dao
interface UserInfoDao {
    @Insert
    suspend fun insertUserInfo(userInfo: UserInfo)

    @Query("SELECT * FROM user_info")
    suspend fun getAllfromUserInfo(): List<UserInfo>
}

@Dao
interface UserUserAddressDao {
    @Insert
    suspend fun insertUserAddress(userAddress: UserAdress)

    @Query("SELECT * FROM user_Adress")
    suspend fun getAllfromUserAdress(): List<UserAdress>
}
@Dao
interface CombinedUserDao{
@Transaction
@Query("SELECT * FROM user_registration LEFT JOIN user_info ON user_registration.userId = user_info.userId LEFT JOIN user_Adress ON user_registration.userId = user_Adress.userId")
fun getUsersWithEducationAndAddress(): LiveData<List<CombinedDataEntity>>?
}
