package com.apps.userlistviewnregistration

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apps.userlistviewnregistration.Database.UserDb
import com.apps.userlistviewnregistration.Database.UserRepository
import com.apps.userlistviewnregistration.UserViewModel.RegistrationViewModel
import com.apps.userlistviewnregistration.UserViewModel.ViewModelFactory
import com.apps.userlistviewnregistration.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
   private lateinit var binding:ActivityMainBinding
    lateinit var viewModel: RegistrationViewModel
//    lateinit var registerdata:List<UserRegistration>
//    lateinit var addressdata :List<UserAdress>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = UserRepository(applicationContext)
        viewModel = ViewModelProvider(this, ViewModelFactory(repo)).get(RegistrationViewModel::class.java)

//        lifecycleScope.launch{
//            registerdata = withContext(Dispatchers.IO) {
//                repo.userRegDao.getAllfromregistration()
//            }
//            addressdata = withContext(Dispatchers.IO) {
//                repo.userAddDao.getAllfromUserAdress()
//            }
//            val combinedList: List<mixedData> = registerdata.map { userRegistration ->
//                mixedData(userRegistration, null)
//            } + addressdata.map { userAddress ->
//                mixedData(null, userAddress)
//            }
//            val customAdapter = CustomAdapter(this@MainActivity, combinedList)
//            binding.listView.adapter = customAdapter
//
//           }


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Users"
        viewModel.getCombinedData()?.observe(this, Observer { combinedDataList ->
            Log.d("data0",combinedDataList.toString())
            val adapter = CustomAdapter(this, combinedDataList)

            binding.listView.adapter = adapter
        })

        binding.register.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}