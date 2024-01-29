package com.apps.userlistviewnregistration.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "user_registration")
data class UserRegistration(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0,
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
    val info_id: Long = 0,
    val userId:Long,
    val education:String?,
    val year_of_passing:Int,
    val grade:String,
    val experience:Int,
    val designation:String,
    val domain:String,
)
@Entity(tableName = "user_Adress")
data class UserAdress(
    @PrimaryKey(autoGenerate = true)
    val add_id: Long = 0,
    val userId: Long,
    val address:String,
    val landmark:String,
    val city:String,
    val state:String,
    val pincode:String
)

data class CombinedDataEntity(
    @Embedded val UserRegistration: UserRegistration,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val educationInfo: UserInfo?,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val addressInfo: UserAdress
)
