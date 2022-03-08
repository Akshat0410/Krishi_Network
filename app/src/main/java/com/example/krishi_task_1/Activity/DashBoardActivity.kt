package com.example.krishi_task_1.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import com.example.krishi_task_1.R

class DashBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val youtube : CardView=findViewById(R.id.youtubePlayerButton)
        val user : CardView =findViewById(R.id.userButton)

        youtube.setOnClickListener {
            val intent= Intent(this, YoutubePlayerActivity::class.java)
            startActivity(intent)
        }

        user.setOnClickListener {
            val intent= Intent(this, UserDetailActivity::class.java)
            startActivity(intent)
        }
    }
}