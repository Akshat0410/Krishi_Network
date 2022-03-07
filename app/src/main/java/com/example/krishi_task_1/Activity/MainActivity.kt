package com.example.krishi_task_1.Activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.krishi_task_1.Config.ImageUtil
import com.example.krishi_task_1.R
import com.example.krishi_task_1.models.User
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson





class MainActivity : AppCompatActivity() {

    private lateinit var profileImage : ImageView
    private lateinit var username : EditText
    private lateinit var userEmail : EditText
    private lateinit var submit : MaterialButton
    private lateinit var bp : Bitmap
    private lateinit var mPrefs : SharedPreferences
    private var MY_PREF_NAME="MY APP"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUI()

        if(getUser()){
            val intent=Intent(this, YoutubePlayerActivity::class.java)
            startActivity(intent)
            finish()
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                bp=data?.extras?.get("data") as Bitmap
                Toast.makeText(this,bp.toString(),Toast.LENGTH_SHORT).show()
                profileImage.setImageBitmap(bp)

            }
        }

        profileImage.setOnClickListener {
            val intent=Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            resultLauncher.launch(intent)
        }


        submit.setOnClickListener {
            val name : String = username.text.toString().trim()
            val email : String = userEmail.text.toString()
            if(name.isNotEmpty() && isValidEmail(email)){
                val user= User(true,name,email,ImageUtil.convertToString(bp)!!)
                saveUser(user)
            }
        }

    }

    private fun saveUser(user: User) {
        val prefsEditor: SharedPreferences.Editor = mPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        prefsEditor.putString("User", json)
        prefsEditor.apply()
    }

    private fun getUser() : Boolean{
        val gson = Gson()
        val json: String? = mPrefs.getString("User", "")
        if(json.isNullOrEmpty())
            return false
        val user : User = gson.fromJson(json, User::class.java)

        return user.doesExist
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun initializeUI() {
        profileImage=findViewById(R.id.profile_image)
        username=findViewById(R.id.nameshow)
        userEmail=findViewById(R.id.email)
        submit=findViewById(R.id.submit)
        mPrefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE)
    }
}