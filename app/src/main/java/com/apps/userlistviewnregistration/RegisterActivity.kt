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
    lateinit var first_name:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setSupportActionBar(binding!!.toolbarregister)
        supportActionBar?.title = "Register"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val repo = UserRepository(applicationContext)
        viewModel = ViewModelProvider(this,ViewModelFactory(repo)).get(RegistrationViewModel::class.java)


//        binding!!.firstNameEditText.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                if(s != null){
//                      first_name = s.toString()
//                } else {
//                first_name = "raw"
//                    // Handle the case when `s` is null (text is cleared or set to null)
//                }
//            }
//
//        })




//        if (isValidMobileNumber(mob)) {
//            val mobileNumber = mob.toLong()
//        } else {
//            binding!!.phoneEditText.error = "Enter a valid 10-digit mobile number"
//        }


//        if (isValidEmail(em)) {
//
//        } else {
//            binding!!.emailEditText.error = "Please Enter a valid email"
//        }

//        if (isValidPassword(password)) {
//
//        } else {
//            binding!!.passwordEditText.error = "Please Enter a valid email"
//        }


//        if(confmpswd.equals(password)){
//
//        }
//        else{
//            binding!!.confirmPasswordEditText.error = "Please confirm your password again"
//        }


        binding!!.nextButton.setOnClickListener{
            val firstname = binding!!.firstNameEditText.text.toString()
            val lastname = binding!!.lastNameEditText.text.toString()
            val mob = binding!!.phoneEditText.text.toString()
            val em = binding!!.emailEditText.text.toString()
            val password = binding!!.passwordEditText.text.toString()
            val confmpswd = binding!!.confirmPasswordEditText.text.toString()


            viewModel.reglivedata.value = UserRegistration(0,firstname,lastname,mob.toLong(),em,password,confmpswd)

            lifecycleScope.launch {
                viewModel.insertUserRegistration()
            }
            startActivity(Intent(this,YourInfo::class.java))
        }



    }

        private fun isValidMobileNumber(mobileNumber: String): Boolean {
            return mobileNumber.length == 10 && mobileNumber.all { it.isDigit() }
        }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",
            Pattern.CASE_INSENSITIVE
        )
        val matcher = emailPattern.matcher(email)
        return matcher.matches()
    }

    private fun isValidPassword(password: String): Boolean {
        // Use a regex pattern for password validation
        val passwordPattern = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()-_+=])[A-Za-z\\d!@#\$%^&*()-_+=]{8,}\$"
        )
        val matcher = passwordPattern.matcher(password)
        return matcher.matches()
    }



}