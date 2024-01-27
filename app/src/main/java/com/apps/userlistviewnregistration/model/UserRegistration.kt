package com.apps.userlistviewnregistration.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_registration")
data class UserRegistration(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "first_name")
    val first_name:String?,
    @ColumnInfo(name = "last_name")
    val last_name:String?,
    @ColumnInfo(name = "Mob_Number")
    val phone:Long?,
    @ColumnInfo(name = "email_id")
    val email:String?,
    @ColumnInfo(name = "password")
    val password:String?,
    @ColumnInfo(name = "c_password")
    val confirm_password:String?
)

@Entity(tableName = "user_info")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var education:String,
    var year_of_passing:String,
    var grade:String,
    var experience:Int,
    var designation:String,
    var domain:String,
)
@Entity(tableName = "user_Adress")
data class UserAdress(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var address:String,
    var landmark:String,
    var city:String,
    var state:String,
    var pincode:String
)
