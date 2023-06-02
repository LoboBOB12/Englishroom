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


class FruitsActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var container: LinearLayout? = null

    private var fruitsRecycler: RecyclerView? = null
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
        setContentView(R.layout.activity_fruits)
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
            R.raw.banana,
            R.raw.cherry,
            R.raw.coconut,
            R.raw.fig,
            R.raw.grape,
            R.raw.green_apple,
            R.raw.kiwi,
            R.raw.papaya,
            R.raw.peach,
            R.raw.pineapple,
            R.raw.pomegranate,
            R.raw.strawberry,
            R.raw.watermelon
        )

        imageItemList = ArrayList()
        initList()
        adapter = ImageAdapter(this, imageItemList)

        fruitsRecycler = findViewById<View>(R.id.recycler_fruits) as RecyclerView
        centerZoomLayoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        fruitsRecycler!!.layoutManager = centerZoomLayoutManager
        fruitsRecycler!!.itemAnimator = DefaultItemAnimator()
        fruitsRecycler!!.adapter = adapter

        previous = findViewById<View>(R.id.previous_fruits) as Button
        play = findViewById<View>(R.id.play_fruits) as Button
        next = findViewById<View>(R.id.next_fruits) as Button

        counter = Int.MAX_VALUE / 2

        previous!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter--
            fruitsRecycler!!.smoothScrollToPosition(counter)
        }

        next!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter++
            fruitsRecycler!!.smoothScrollToPosition(counter)
        }

        fruitsRecycler!!.scrollToPosition(counter)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(fruitsRecycler)

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
        imageItemList!!.add(ImageItem("Banana", R.drawable.banana))
        imageItemList!!.add(ImageItem("Cherry", R.drawable.cherry))
        imageItemList!!.add(ImageItem("Coconut", R.drawable.coconut))
        imageItemList!!.add(ImageItem("Fig", R.drawable.dragon))
        imageItemList!!.add(ImageItem("Grapes", R.drawable.grape))
        imageItemList!!.add(ImageItem("Green Apple", R.drawable.greenapple))
        imageItemList!!.add(ImageItem("Kiwi", R.drawable.kiwi))
        imageItemList!!.add(ImageItem("Papaya", R.drawable.papaya))
        imageItemList!!.add(ImageItem("Peach", R.drawable.peach))
        imageItemList!!.add(ImageItem("Pineapple", R.drawable.pine))
        imageItemList!!.add(ImageItem("Pomegranate", R.drawable.pomegranate))
        imageItemList!!.add(ImageItem("Strawberry", R.drawable.strawberry))
        imageItemList!!.add(ImageItem("Watermelon", R.drawable.watermelon))
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