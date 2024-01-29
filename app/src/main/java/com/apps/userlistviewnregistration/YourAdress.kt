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
    var selectedstate:String? = ""
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




        binding.btnSubmit.setOnClickListener {
            val address = binding.etaddress.text.toString()
            val landmark = binding.etlandmark.text.toString()
            val pincode = binding.etpin.text.toString()
            val city = binding.etcity.text.toString()

            if (isValidAdress(address) && isValidLandmark(landmark) && isValidCity(city)
                && isValidState(selectedstate!!) && isValidPincode(pincode)
            ) {
                viewModel.addlivedata.value =
                    UserAdress(0, 0, address, landmark, city, selectedstate!!, pincode)

                lifecycleScope.launch {
                    viewModel.insertUserAddress()
                    startActivity(Intent(this@YourAdress, MainActivity::class.java))
                }
            }
        }

    }
    private fun isValidAdress(address:String):Boolean{
        if (address.isEmpty()) {
            binding.etaddress.error = "Please Enter your address"
            return false
        }
        else if (address.length <= 3 || !address.all { it.isLetter() }) {
            binding.etaddress.error = "Length should be greater than 3 or Only letters allowed"
            return false
        }
        return true
    }

    private fun isValidLandmark(landmark:String):Boolean{
        if (landmark.isEmpty()) {
            binding.etlandmark.error = "Please Enter your landmark"
            return false
        }
        else if (landmark.length <= 3 || !landmark.all { it.isLetter() }) {
            binding.etlandmark.error = "Length should be greater than 3 or Only letters allowed"
            return false
        }
        return true
    }

    private fun isValidCity(city:String):Boolean{
        if (city.isEmpty()) {
            binding.etcity.error = "Please Enter your city"
            return false
        }
        else if (city.length <= 3 || !city.all { it.isLetter() }) {
            binding.etcity.error = "Length should be greater than 3 or Only letters allowed"
            return false
        }
        return true
    }

    private fun isValidPincode(pincode : String):Boolean{
        if (pincode.isEmpty()) {
            binding.etpin.error = "Please Enter your Zip/Pin code"
            return false
        }
        else if (pincode.length != 6 || !pincode.all { it.isDigit() }) {
            binding.etpin.error = "Enter only 6 digit"
            return false
        }
        return true
    }

    private fun isValidState(state:String):Boolean{
        if (state.isEmpty()) {
            binding.dropstate.error = "Please Enter your state"
            return false
        }
        return true
    }

}