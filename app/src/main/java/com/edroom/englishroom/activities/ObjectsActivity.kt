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
import com.edroom.englishroom.adapters.ImageAdapter
import com.edroom.englishroom.helper.AdController
import com.edroom.englishroom.helper.CenterZoomLayoutManager
import com.edroom.englishroom.helper.ImageItem


class ObjectsActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var container: LinearLayout? = null

    private var objectRecycler: RecyclerView? = null
    private var imageItemList: MutableList<ImageItem>? = null
    private var adapter: ImageAdapter? = null

    private var centerZoomLayoutManager: CenterZoomLayoutManager? = null

    private var previous: Button? = null
    private var play:Button? = null
    private var next:Button? = null
    private var counter = 0

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var sounds: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objects)
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
            R.raw.air_conditioner,
            R.raw.briefcase,
            R.raw.bucket,
            R.raw.clock,
            R.raw.cricket_bat,
            R.raw.cricket_ball,
            R.raw.chair,
            R.raw.pencil,
            R.raw.fan,
            R.raw.ladder,
            R.raw.laptop,
            R.raw.pen,
            R.raw.scissor,
            R.raw.smartphone,
            R.raw.toothbrush,
            R.raw.uchiwa
        )

        imageItemList = ArrayList()
        initList()
        adapter = ImageAdapter(this, imageItemList)

        objectRecycler = findViewById<View>(R.id.recycler_objects) as RecyclerView
        centerZoomLayoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        objectRecycler!!.layoutManager = centerZoomLayoutManager
        objectRecycler!!.itemAnimator = DefaultItemAnimator()
        objectRecycler!!.adapter = adapter

        previous = findViewById<View>(R.id.previous_objects) as Button
        play = findViewById<View>(R.id.play_objects) as Button
        next = findViewById<View>(R.id.next_objects) as Button

        counter = Int.MAX_VALUE / 2

        previous!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter--
            objectRecycler!!.smoothScrollToPosition(counter)
        }

        next!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter++
            objectRecycler!!.smoothScrollToPosition(counter)
        }

        objectRecycler!!.scrollToPosition(counter)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(objectRecycler)

        play!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            val pos = counter % (imageItemList as ArrayList<ImageItem>).size
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer.create(applicationContext, sounds[pos])
            mediaPlayer!!.start()
        }

    }

    private fun initList() {
        imageItemList!!.add(ImageItem("Air Conditioner", R.drawable.air_conditioner))
        imageItemList!!.add(ImageItem("Briefcase", R.drawable.briefcase))
        imageItemList!!.add(ImageItem("Bucket", R.drawable.bucket))
        imageItemList!!.add(ImageItem("Clock", R.drawable.clock))
        imageItemList!!.add(ImageItem("Cricket Bat", R.drawable.cricket_bat))
        imageItemList!!.add(ImageItem("Cricket Ball", R.drawable.cricket_ball))
        imageItemList!!.add(ImageItem("Chair", R.drawable.chair))
        imageItemList!!.add(ImageItem("Pencil", R.drawable.pencil))
        imageItemList!!.add(ImageItem("Fan", R.drawable.fan))
        imageItemList!!.add(ImageItem("Ladder", R.drawable.ladder))
        imageItemList!!.add(ImageItem("Laptop", R.drawable.laptop))
        imageItemList!!.add(ImageItem("Pen", R.drawable.pen))
        imageItemList!!.add(ImageItem("Scissors", R.drawable.scissors))
        imageItemList!!.add(ImageItem("SmartPhone", R.drawable.smartphone))
        imageItemList!!.add(ImageItem("Toothbrush", R.drawable.toothbrush))
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