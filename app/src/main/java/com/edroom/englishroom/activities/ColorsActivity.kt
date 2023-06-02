package com.edroom.englishroom.activities

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.edroom.englishroom.R
import com.edroom.englishroom.adapters.ColorsAdapter
import com.edroom.englishroom.helper.AdController
import com.edroom.englishroom.helper.CenterZoomLayoutManager

class ColorsActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var container: LinearLayout? = null

    private var colorRecycler: RecyclerView? = null
    private var adapter: ColorsAdapter? = null
    private var centerZoomLayoutManager: CenterZoomLayoutManager? = null

    private var previous: Button? = null
    private  var play:Button? = null
    private  var next:Button? = null
    private var counter = 0
    private lateinit var sounds: IntArray
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)
        activity = this

        initViews()
        contentViews()
    }

    private fun initViews() {
        backBtn = findViewById(R.id.backBtn)
        container = findViewById(R.id.banner_container)
    }

    private fun contentViews() {
        backBtn!!.setOnClickListener {
            onBackPressed()
        }

        /*Admob*/
        AdController.largeBannerAd(activity, container)
        AdController.loadInterAd(activity)

        sounds = intArrayOf(
            R.raw.red,
            R.raw.pink,
            R.raw.purple,
            R.raw.indigo,
            R.raw.blue,
            R.raw.sky_blue,
            R.raw.cyan,
            R.raw.teal,
            R.raw.green,
            R.raw.lime,
            R.raw.yellow,
            R.raw.amber,
            R.raw.orange,
            R.raw.brown,
            R.raw.grey,
            R.raw.black,
            R.raw.white
        )

        val colors = applicationContext.resources.getIntArray(R.array.colors)
        adapter = ColorsAdapter(applicationContext)
        centerZoomLayoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        colorRecycler = findViewById<View>(R.id.recycler_colors) as RecyclerView
        previous = findViewById<View>(R.id.previous_colors) as Button
        play = findViewById<View>(R.id.play_colors) as Button
        next = findViewById<View>(R.id.next_colors) as Button

        colorRecycler!!.layoutManager = centerZoomLayoutManager
        colorRecycler!!.itemAnimator = DefaultItemAnimator()
        colorRecycler!!.adapter = adapter

        counter = Int.MAX_VALUE / 2

        previous!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter--
            if (counter < 0) {
                counter = colors.size - 1
                colorRecycler!!.scrollToPosition(counter)
            }
            colorRecycler!!.smoothScrollToPosition(counter)
        }

        next!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter++
            colorRecycler!!.smoothScrollToPosition(counter)
        }

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(colorRecycler)

        play!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            val pos = counter % colors.size
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer.create(applicationContext, sounds[pos])
            mediaPlayer!!.start()
        }

    }

    override fun onBackPressed() {
        AdController.adCounter++
        if (AdController.adCounter == AdController.adDisplayCounter) {
            AdController.showInterstitialAd(this)
        } else {
            super.onBackPressed()
        }
    }

}