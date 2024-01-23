package com.apps.userlistviewnregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.userlistviewnregistration.databinding.ActivityYourAdressBinding

class YourAdress : AppCompatActivity() {
    lateinit var binding:ActivityYourAdressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.infoToolbar)
        supportActionBar?.title = "Your Address"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}