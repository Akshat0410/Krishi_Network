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
    private lateinit var youtube: YouTubePlayer
    private var currentTime: Int = 0
    private lateinit var mPrefs : SharedPreferences
    private var MY_PREF_NAME="MY APP"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player)
        initYoutubePlayer()
        initializePlayer()

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, UserDetailActivity::class.java))
            finish()
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt("time", currentTime)
        super.onSaveInstanceState(outState, outPersistentState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentTime = savedInstanceState.getInt("time")
        Log.e("current_time", currentTime.toString())
    }

    private fun initializePlayer() {
        youTubePlayer.initialize(YoutubeConfig.GOOGLE_API_KEY, youtubePlayerInIt)
    }

    private fun initYoutubePlayer() {

        youTubePlayer = findViewById(R.id.youtubePlayerView)
        play = findViewById(R.id.playVideo)

        youtubePlayerInIt = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(getVideoId())

                p1?.setOnFullscreenListener {
                    currentTime = p1.currentTimeMillis
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Cannot Initialize", Toast.LENGTH_SHORT).show()
            }


        }


        play.setOnClickListener {
            youTubePlayer.initialize(YoutubeConfig.GOOGLE_API_KEY, youtubePlayerInIt)
        }
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