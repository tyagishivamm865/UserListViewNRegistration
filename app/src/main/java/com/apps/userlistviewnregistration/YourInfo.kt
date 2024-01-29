package com.apps.userlistviewnregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apps.userlistviewnregistration.Database.UserDb
import com.apps.userlistviewnregistration.Database.UserRepository
import com.apps.userlistviewnregistration.UserViewModel.RegistrationViewModel
import com.apps.userlistviewnregistration.UserViewModel.ViewModelFactory
import com.apps.userlistviewnregistration.databinding.ActivityYourInfoBinding
import com.apps.userlistviewnregistration.model.UserInfo
import kotlinx.coroutines.launch

class YourInfo : AppCompatActivity() {
    lateinit var viewModel:RegistrationViewModel
    lateinit var binding:ActivityYourInfoBinding
    lateinit var eduselected:String
    lateinit var desiselected : String
    lateinit var domselected:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = UserRepository(applicationContext)
        viewModel = ViewModelProvider(this, ViewModelFactory(repo)).get(RegistrationViewModel::class.java)

        setSupportActionBar(binding.infoToolbar)
        supportActionBar?.title = "Your Info"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val education_values = arrayOf("Post Graduate", "Graduate", "HSC/Diploma", "SSC 4")
        val designation_values = arrayOf("Software Engineer", "Trainee Engineer", "Front-End Dev", "Back-End Dev")
        val domain_values = arrayOf("Android", "Ios", "FullStack", "DevOps","Cloud")

        val arrayAdapter_edu =
            ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, education_values)
        binding.dropedu.setAdapter(arrayAdapter_edu)

        binding.dropedu.setOnItemClickListener { parent, view, position, id ->
            eduselected = parent.adapter.getItem(position).toString()

        }

        val arrayAdapter_designation =
            ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, designation_values)

        binding.dropdesignation.setAdapter(arrayAdapter_designation)
        binding.dropdesignation.setOnItemClickListener { parent, view, position, id ->
            val selectedValue = parent.adapter.getItem(position)
            desiselected = selectedValue.toString()
        }
        val arrayAdapter_domain =
            ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,domain_values)

        arrayAdapter_domain.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.dropDomain.setAdapter(arrayAdapter_domain)
        binding.dropDomain.setOnItemClickListener { parent, view, position, id ->
            val selectedValue = parent.adapter.getItem(position)
            Toast.makeText(this,"$selectedValue",Toast.LENGTH_LONG).show()
            domselected = selectedValue.toString()
        }




        binding.btnNext.setOnClickListener{


            val yearofpassing = binding.etYop.text.toString()
            val experience = binding.etExperience.text.toString()

            val grade = binding.etgrade.text.toString()
            viewModel.infolivedata.value = UserInfo(0,0,eduselected,yearofpassing,grade,experience.toInt(),desiselected,domselected)
            lifecycleScope.launch{
               viewModel.insertUserInfo()
            }
             startActivity(Intent(this,YourAdress::class.java))
         }
    }
}