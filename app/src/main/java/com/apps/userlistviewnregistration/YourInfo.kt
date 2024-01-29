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
    var eduselected:String? = ""
    var desiselected : String? = ""
    var domselected:String? = ""
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

            if(isvalideducation(eduselected!!) && isvalidpassingyear(yearofpassing) && isvalidGrade(grade)
                && isvalidexperience(experience) && isvalidDesignation(desiselected!!) && isvalidedomain(domselected!!)) {
                viewModel.infolivedata.value = UserInfo(
                    0,
                    0,
                    eduselected,
                    yearofpassing.toInt(),
                    grade,
                    experience.toInt(),
                    desiselected!!,
                    domselected!!
                )
                lifecycleScope.launch {
                    viewModel.insertUserInfo()
                }
                startActivity(Intent(this, YourAdress::class.java))
            }
            else {
                return@setOnClickListener
            }
         }

        }

        fun isvalideducation(ed:String):Boolean{
            if(ed.isEmpty()){
                binding.dropedu.error = "Please select your education"
                return false
            }
            return true
        }

        fun isvalidDesignation(designation:String):Boolean{
            if(designation.isEmpty()){
                binding.dropdesignation.error = "Please select your designation"
                return false
            }
            return true
        }

        fun isvalidedomain(domain:String):Boolean{
            if(domain.isEmpty()){
                binding.dropDomain.error = "Please select your domain"
                return false
            }
            return true
        }

        fun isvalidpassingyear(pyear:String):Boolean{
            if(pyear.isEmpty()){
                binding.etYop.error = "Please select your domain"
                return false
            }
            if (!pyear.all { it.isDigit() } || pyear.length!=4) {
                binding.etYop.error = "Please enter valid year"
                return false
            }
            return true
        }

        fun isvalidGrade(grade:String):Boolean{
            if (grade.isEmpty()) {
                binding.etgrade.error = "Please enter your grade"
                return false
            }

            if (!grade.all { it.isLetter() || it.isDigit() } || grade.length>2) {
                binding.etgrade.error = "grade can only be letter or digit"
                return false
            }
            return true
        }

        fun isvalidexperience(exp:String):Boolean{
            if (exp.isEmpty()) {
                binding.etExperience.error = "Please enter your experience"
                return false
            }

            if (!exp.all { it.isDigit() } || exp.length>2) {
                binding.etExperience.error = "experience can only be less than 100"
                return false
            }
            return true
        }

    }