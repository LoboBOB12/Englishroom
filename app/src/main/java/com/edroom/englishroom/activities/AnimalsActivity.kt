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

class AnimalsActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var container: LinearLayout? = null

    private var animalRecycler: RecyclerView? = null
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
        setContentView(R.layout.activity_animals)
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
            R.raw.alligator,
            R.raw.bear,
            R.raw.elephant,
            R.raw.lion,
            R.raw.monkey,
            R.raw.panda,
            R.raw.rabbit,
            R.raw.snake,
            R.raw.squirrel,
            R.raw.tiger,
            R.raw.zebra
        )

        imageItemList = ArrayList()
        initList()
        adapter = ImageAdapter(this, imageItemList)

        animalRecycler = findViewById<View>(R.id.recyclerViewAnimals) as RecyclerView
        centerZoomLayoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        animalRecycler!!.layoutManager = centerZoomLayoutManager
        animalRecycler!!.itemAnimator = DefaultItemAnimator()
        animalRecycler!!.adapter = adapter

        previous = findViewById<View>(R.id.previous_animals) as Button
        play = findViewById<View>(R.id.play_animals) as Button
        next = findViewById<View>(R.id.next_animals) as Button

        counter = Int.MAX_VALUE / 2

        previous!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter--
            animalRecycler!!.smoothScrollToPosition(counter)
        }

        next!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter++
            animalRecycler!!.smoothScrollToPosition(counter)
        }

        animalRecycler!!.scrollToPosition(counter)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(animalRecycler)

        play!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            val pos = counter % imageItemList!!.size
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer.create(applicationContext, sounds[pos])
            mediaPlayer!!.start()
        }
    }

    private fun initList() {
        imageItemList!!.add(ImageItem("Alligator", R.drawable.alligator))
        imageItemList!!.add(ImageItem("Bear", R.drawable.bear))
        imageItemList!!.add(ImageItem("Elephant", R.drawable.elephant))
        imageItemList!!.add(ImageItem("Lion", R.drawable.lion))
        imageItemList!!.add(ImageItem("Monkey", R.drawable.monkey))
        imageItemList!!.add(ImageItem("Panda", R.drawable.panda))
        imageItemList!!.add(ImageItem("Rabbit", R.drawable.rabbit))
        imageItemList!!.add(ImageItem("Snake", R.drawable.snake))
        imageItemList!!.add(ImageItem("Squirrel", R.drawable.squirrel))
        imageItemList!!.add(ImageItem("Tiger", R.drawable.tiger))
        imageItemList!!.add(ImageItem("Zebra", R.drawable.zebra))
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