package com.apps.userlistviewnregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apps.userlistviewnregistration.Database.UserDb
import com.apps.userlistviewnregistration.Database.UserRepository
import com.apps.userlistviewnregistration.UserViewModel.RegistrationViewModel
import com.apps.userlistviewnregistration.UserViewModel.ViewModelFactory
import com.apps.userlistviewnregistration.databinding.ActivityYourAdressBinding
import com.apps.userlistviewnregistration.model.UserAdress
import com.apps.userlistviewnregistration.model.UserInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class YourAdress : AppCompatActivity() {
    lateinit var binding:ActivityYourAdressBinding
    lateinit var viewModel:RegistrationViewModel
    lateinit var selectedstate:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = UserRepository(applicationContext)
        viewModel = ViewModelProvider(this, ViewModelFactory(repo)).get(RegistrationViewModel::class.java)


        setSupportActionBar(binding.infoToolbar)
        supportActionBar?.title = "Your Address"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val arrayofstates = arrayOf("Maharashtra", "Gujarat", "Karnataka", "Madhya Pradesh","Delhi", "Others")
        val stateadapter = ArrayAdapter(this@YourAdress,android.R.layout.simple_spinner_dropdown_item,arrayofstates)
        binding.dropstate.setAdapter(stateadapter)

        binding.dropstate.setOnItemClickListener { parent, view, position, id ->
             selectedstate = parent.adapter.getItem(position).toString()
        }




        binding.btnSubmit.setOnClickListener{
            val address = binding.etaddress.text.toString()
            val landmark = binding.etlandmark.text.toString()
            val pincode = binding.etpin.text.toString()
            val city = binding.etcity.text.toString()

            viewModel.addlivedata.value = UserAdress(0,0,address,landmark,city,selectedstate,pincode)

            lifecycleScope.launch {
                viewModel.insertUserAddress()
                startActivity(Intent(this@YourAdress,MainActivity::class.java))
            }
        }

    }
}