package com.apps.userlistviewnregistration.UserViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apps.userlistviewnregistration.Database.UserRepository
import com.apps.userlistviewnregistration.model.UserAdress
import com.apps.userlistviewnregistration.model.UserInfo
import com.apps.userlistviewnregistration.model.UserRegistration


class RegistrationViewModel(private val userRepository: UserRepository) : ViewModel() {

    var infolivedata = MutableLiveData<UserInfo>()
    var reglivedata = MutableLiveData<UserRegistration>()
    var addlivedata = MutableLiveData<UserAdress>()

    suspend fun insertUserInfo() {
               infolivedata.let {
                   userRepository.insertUserInfo(infolivedata.value!!)
               }
    }

    suspend fun insertUserRegistration() {
        reglivedata.let {
            userRepository.insertUserRegistration(reglivedata.value!!)
        }
    }

    suspend fun insertUserAddress() {
        addlivedata.let {
            userRepository.insertUserAddress(addlivedata.value!!)
        }
    }

}
