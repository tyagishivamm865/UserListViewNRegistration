package com.apps.userlistviewnregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apps.userlistviewnregistration.Database.UserDb
import com.apps.userlistviewnregistration.Database.UserRepository
import com.apps.userlistviewnregistration.UserViewModel.RegistrationViewModel
import com.apps.userlistviewnregistration.UserViewModel.ViewModelFactory
import com.apps.userlistviewnregistration.databinding.ActivityRegisterBinding
import com.apps.userlistviewnregistration.model.UserInfo
import com.apps.userlistviewnregistration.model.UserRegistration
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    var binding : ActivityRegisterBinding? = null
    lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setSupportActionBar(binding!!.toolbarregister)
        supportActionBar?.title = "Register"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val repo = UserRepository(applicationContext)
        viewModel = ViewModelProvider(this,ViewModelFactory(repo)).get(RegistrationViewModel::class.java)


        binding!!.nextButton.setOnClickListener{
            val firstname = binding!!.firstNameEditText.text.toString()
            val lastname = binding!!.lastNameEditText.text.toString()
            val mob = binding!!.phoneEditText.text.toString()
            val em = binding!!.emailEditText.text.toString()
            val password = binding!!.passwordEditText.text.toString()
            val confmpswd = binding!!.confirmPasswordEditText.text.toString()

            if(isValidFirstName(firstname) && isValidLastName(lastname) && isValidMobileNumber(mob) && isValidEmail(em)
                && isValidPassword(password) && isValidConfirmpassword(password,confmpswd)){
                viewModel.reglivedata.value =
                    UserRegistration(0, firstname, lastname, mob.toLong(), em, password, confmpswd)

                lifecycleScope.launch {
                    viewModel.insertUserRegistration()
                }
                startActivity(Intent(this, YourInfo::class.java))
            }
            else{
                return@setOnClickListener
            }
        }

    }

    private fun isValidFirstName(firstname:String):Boolean{
        if (firstname.isEmpty()) {
            binding!!.firstNameEditText.error = "Please Enter the Firstname"
            return false
        }
       else if (firstname.length <= 3 || !firstname.all { it.isLetter() }) {
            binding!!.firstNameEditText.error = "Length should be greater than 3 or Only letters allowed"
            return false
        }
        return true
    }

    private fun isValidLastName(lastname:String):Boolean{
        if (lastname.isEmpty()) {
            binding!!.lastNameEditText.error = "Please Enter the Lastname please"
            return false
        }
       else if (lastname.length <= 3 || !lastname.all { it.isLetter() }) {
            binding!!.lastNameEditText.error = "Length should be greater than 3 or Only letters allowed"
            return false
        }
        return true
    }

    private fun isValidMobileNumber(mobileNumber: String):Boolean{
        if(mobileNumber.length == 10 && mobileNumber.all { it.isDigit() }){
            return true
        }
        else if(mobileNumber.isEmpty()){
            binding!!.phoneEditText.error = "Enter 10 digit mobile no."
            return false
        }
        binding!!.phoneEditText.error = "Enter the valid 10 digit mobile no."
        return false
    }

    private fun isValidEmail(email: String): Boolean {
        if(email.isEmpty()){
            binding!!.emailEditText.error = "Please Enter the email"
            return false
        }
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",
            Pattern.CASE_INSENSITIVE
        )
        val matcher = emailPattern.matcher(email)
         if(matcher.matches()==false){
             binding!!.emailEditText.error = "Please Enter the valid email"
             return false
         }
        return true
    }


    private fun isValidPassword(password: String): Boolean {
        if(password.isEmpty()){
            binding!!.passwordEditText.error = "Please Enter the phone number"
            return false
        }
        val passwordPattern = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()-_+=])[A-Za-z\\d!@#\$%^&*()-_+=]{8,}\$"
        )
        val matcher = passwordPattern.matcher(password)

        if(!matcher.matches()){
            binding!!.passwordEditText.error = "Please Enter the valid password"
            return false
        }
        return true
    }

    private fun isValidConfirmpassword(password: String,confmpswd: String):Boolean{
        if (password != confmpswd) {
            binding!!.confirmPasswordEditText.error = "Please Enter the correct confirm password"
            return false
        }
        else if(confmpswd.isEmpty()){
            binding!!.confirmPasswordEditText.error = "Please Enter the confirm password"
            return false
        }
        else {
            return true
        }
    }


}