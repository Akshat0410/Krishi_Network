package com.example.krishi_task_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var youTubePlayer:YouTubePlayerView
    private lateinit var mOnInitializerListener: YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initYoutubePlayer()
    }

    private fun initYoutubePlayer() {
        youTubePlayer=findViewById(R.id.youtubePlayerView)
        mOnInitializerListener
    }
}