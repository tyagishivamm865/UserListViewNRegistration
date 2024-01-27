package com.apps.userlistviewnregistration.UserViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apps.userlistviewnregistration.Database.UserRepository

@Suppress("UNCHECKED_CAST", "unused")
@JvmSuppressWildcards
class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
