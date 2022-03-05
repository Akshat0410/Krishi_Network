package com.example.krishi_task_1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.krishi_task_1.Config.YoutubeConfig
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerView

class YoutubePlayerActivity : YouTubeBaseActivity() {

    private lateinit var youTubePlayer : YouTubePlayerView
    private lateinit var play : Button
    private lateinit var youtubePlayerInIt : YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player)
        initYoutubePlayer()
    }

    private fun initYoutubePlayer() {

        youTubePlayer=findViewById(R.id.youtubePlayerView)
        play=findViewById(R.id.playVideo)

        youtubePlayerInIt= object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(getVideoId())
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext,"Cannot Initialize",Toast.LENGTH_SHORT).show()
            }

        }

        play.setOnClickListener {
            youTubePlayer.initialize(YoutubeConfig.GOOGLE_API_KEY,youtubePlayerInIt)
        }
    }

    private fun getVideoId(): String {
        val id= listOf<String>("IEF6mw7eK4s", "8CEJoCr_9UI", "344u6uK9qeQ", "3-nM3yNi3wg", "RlcY37n5j9M", "nB7nGcW9TyE")
        return id.random()
    }

}