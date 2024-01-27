package com.apps.userlistviewnregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.apps.userlistviewnregistration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = mutableListOf<String>("Shivam", "amit", "hello", "world", "team rakesh")
        data.add("sfkas")
        data.add("sfkas")


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Users"

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)

        binding.listView.adapter = adapter

        binding.register.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}