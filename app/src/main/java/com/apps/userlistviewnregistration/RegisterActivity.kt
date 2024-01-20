package com.apps.userlistviewnregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.userlistviewnregistration.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    var binding : ActivityRegisterBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setSupportActionBar(binding!!.toolbarregister)
        supportActionBar?.title = "Register"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}