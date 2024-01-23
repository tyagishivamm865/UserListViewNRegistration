package com.apps.userlistviewnregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.apps.userlistviewnregistration.databinding.ActivityYourInfoBinding

class YourInfo : AppCompatActivity() {
    lateinit var binding:ActivityYourInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.infoToolbar)
        supportActionBar?.title = "Your Info"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
         binding.Button03.setOnClickListener{
             startActivity(Intent(this,YourAdress::class.java))
         }
    }
}