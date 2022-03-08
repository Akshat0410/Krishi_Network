package com.example.krishi_task_1.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.krishi_task_1.Config.YoutubeConfig
import com.example.krishi_task_1.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YoutubePlayerActivity : YouTubeBaseActivity() {

    private lateinit var youTubePlayer: YouTubePlayerView
    private lateinit var play: Button
    private lateinit var youtubePlayerInIt: YouTubePlayer.OnInitializedListener
    private lateinit var youtubePlayer: YouTubePlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player)

        youTubePlayer = findViewById(R.id.youtubePlayerView)
        play = findViewById(R.id.playVideo)

        initializePlayer()

        play.setOnClickListener {

            youtubePlayer.loadVideo(getVideoId())

        }

    }

    private fun initializePlayer() {

        youtubePlayerInIt = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                if (p1 != null) {
                    youtubePlayer = p1
                }
                p1?.cueVideo(getVideoId())

            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Cannot Initialize", Toast.LENGTH_SHORT).show()
            }


        }

        youTubePlayer.initialize(YoutubeConfig.GOOGLE_API_KEY, youtubePlayerInIt)


    }

    private fun getVideoId(): String {
        val id = listOf<String>(
            "IEF6mw7eK4s",
            "8CEJoCr_9UI",
            "344u6uK9qeQ",
            "3-nM3yNi3wg",
            "RlcY37n5j9M",
            "nB7nGcW9TyE"
        )
        return id.random()
    }

}