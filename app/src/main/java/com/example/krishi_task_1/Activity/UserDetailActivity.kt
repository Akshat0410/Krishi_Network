package com.example.krishi_task_1.Activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.krishi_task_1.Config.ImageUtil
import com.example.krishi_task_1.R
import com.example.krishi_task_1.models.User
import com.google.gson.Gson

class UserDetailActivity : AppCompatActivity() {


    private lateinit var user : User
    private lateinit var mPrefs : SharedPreferences
    private var MY_PREF_NAME="MY APP"
    private lateinit var userImage : ImageView
    private lateinit var userName : TextView
    private lateinit var userEmail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        user=getUserData()
        initializeViews()

        setUserProfile(user)
    }

    private fun setUserProfile(user: User) {

        userImage.setImageBitmap(ImageUtil.convertToBitmap(user.bp))
        userName.text=user.name
        userEmail.text=user.email

    }

    private fun initializeViews() {
        userImage=findViewById(R.id.profile_image_circular)
        userName=findViewById(R.id.displayName)
        userEmail=findViewById(R.id.displayEmail)
    }

    private fun getUserData(): User {
        mPrefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE)
        val gson = Gson()
        val json: String? = mPrefs.getString("User", "")

        return gson.fromJson(json, User::class.java)
    }
}