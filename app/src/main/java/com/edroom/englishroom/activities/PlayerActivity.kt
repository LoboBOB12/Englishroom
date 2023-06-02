package com.edroom.englishroom.activities

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.edroom.englishroom.R
import com.edroom.englishroom.helper.AdController
import java.util.concurrent.TimeUnit


class PlayerActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var headerTitle: TextView? = null
    private var container: LinearLayout? = null

    private var playPauseBtn: Button? = null
    private var mediaPlayer: MediaPlayer? = null
    private var startTime = 0.0
    private var finalTime = 0.0
    var oneTimeOnly = 0
    private var myHandler: Handler = Handler()
    private var seekBar: SeekBar? = null

    private var startTimeTxt: TextView? = null
    private var finalTimeTxt: TextView? = null
    private var lyricsTxt: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        activity = this

        initViews()
        contentViews()
    }

    private fun initViews() {
        backBtn = findViewById(R.id.backBtn)
        headerTitle = findViewById(R.id.headerTitle)
        container = findViewById(R.id.banner_container)

        playPauseBtn = findViewById(R.id.playPauseBtn)
        seekBar = findViewById(R.id.seekBar)
        startTimeTxt = findViewById(R.id.startTimeTxt)
        finalTimeTxt = findViewById(R.id.finalTimeTxt)
        lyricsTxt = findViewById(R.id.lyricsTxt)
    }

    private fun contentViews() {
        backBtn!!.setOnClickListener {
            onBackPressed()
        }

        /*Admob*/
        AdController.loadBannerAd(activity, container)
        AdController.loadInterAd(activity)

        mediaPlayer = MediaPlayer.create(this, intent.getIntExtra("songAudio", 0))
        seekBar!!.isClickable = false

        headerTitle!!.text = intent.getStringExtra("songTitle")
        lyricsTxt!!.text = intent.getStringExtra("lyrics")

        playPauseBtn!!.setOnClickListener {
            if (mediaPlayer!!.isPlaying) {
                playPauseBtn!!.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_play)
                mediaPlayer!!.pause()

            } else {
                playPauseBtn!!.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_pause)
                mediaPlayer!!.start()

                finalTime = mediaPlayer!!.duration.toDouble()
                startTime = mediaPlayer!!.currentPosition.toDouble()

                if (oneTimeOnly == 0) {
                    seekBar!!.max = finalTime.toInt()
                    oneTimeOnly = 1
                }

                finalTimeTxt!!.text = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
                    TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(
                        finalTime.toLong()
                    )))

                startTimeTxt!!.text = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                    TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(
                        startTime.toLong()
                    )))

                seekBar!!.progress = startTime.toInt();
                myHandler.postDelayed(updateSongTime,100)
            }
        }

        mediaPlayer!!.setOnCompletionListener {
            playPauseBtn!!.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_play)
        }


    }

    private val updateSongTime: Runnable = object : Runnable {
        override fun run() {
            startTime = mediaPlayer!!.currentPosition.toDouble()
            startTimeTxt!!.text = String.format(
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()))
            )
            seekBar!!.progress = startTime.toInt()
            myHandler.postDelayed(this, 100)
        }
    }

    override fun onBackPressed() {
        AdController.adCounter++
        if (AdController.adCounter == AdController.adDisplayCounter) {
            AdController.showInterstitialAd(this)
        } else {
            mediaPlayer!!.stop()
            super.onBackPressed()
        }
    }

}