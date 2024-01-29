package com.apps.userlistviewnregistration

import android.app.Dialog
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apps.userlistviewnregistration.Database.UserDb
import com.apps.userlistviewnregistration.Database.UserRepository
import com.apps.userlistviewnregistration.UserViewModel.RegistrationViewModel
import com.apps.userlistviewnregistration.UserViewModel.ViewModelFactory
import com.apps.userlistviewnregistration.databinding.ActivityMainBinding
import com.apps.userlistviewnregistration.model.CombinedDataEntity
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
   private lateinit var binding:ActivityMainBinding
    lateinit var viewModel: RegistrationViewModel
    lateinit var alldata :List<CombinedDataEntity>
//    lateinit var addressdata :List<UserAdress>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = UserRepository(applicationContext)
        viewModel = ViewModelProvider(this, ViewModelFactory(repo)).get(RegistrationViewModel::class.java)

        lifecycleScope.launch {
            alldata = viewModel.getmixdata()!!
        }

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

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.userinfo_dialog_layout)
            val fullname = dialog.findViewById<TextView>(R.id.txtfullname)
            val demail = dialog.findViewById<TextView>(R.id.txtemail)
            val dphone = dialog.findViewById<TextView>(R.id.txtphone)
            val ddesignation = dialog.findViewById<TextView>(R.id.txtdesignation)
            val daddress = dialog.findViewById<TextView>(R.id.txtAddress)
            val dlandmark = dialog.findViewById<TextView>(R.id.txtlandmark)
            val dcity = dialog.findViewById<TextView>(R.id.txtcitystate)
            val dpincode = dialog.findViewById<TextView>(R.id.txtpin)
            val deducation = dialog.findViewById<TextView>(R.id.txteducation)
            val dpassingyear = dialog.findViewById<TextView>(R.id.txtyearofpassing)
            val dcgpa = dialog.findViewById<TextView>(R.id.txtcgpa)
            val dexperience = dialog.findViewById<TextView>(R.id.txtyearofexperience)
            val ddomain = dialog.findViewById<TextView>(R.id.txtdomain)

           alldata[position].UserRegistration.apply {
               fullname.text = "${first_name} ${last_name}"
               dphone.text = phone.toString()
               demail.text = email.toString()
           }

            alldata[position].educationInfo?.apply {
                deducation.text = education
                dpassingyear.text = year_of_passing
                dcgpa.text = grade
                dexperience.text = experience.toString()
                ddesignation.text = designation
                ddomain.text = domain
            }

            alldata[position].addressInfo.apply {
                daddress.text = address
                dlandmark.text = landmark
                dcity.text = city
                dpincode.text = pincode
            }


            val cancelButton = dialog.findViewById<ImageView>(R.id.imgcancel)
            cancelButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        binding.register.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}