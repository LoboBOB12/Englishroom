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
import com.edroom.englishroom.adapters.ShapesAdapter
import com.edroom.englishroom.helper.AdController
import com.edroom.englishroom.helper.CenterZoomLayoutManager
import com.edroom.englishroom.helper.ImageItem

class ShapesActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var container: LinearLayout? = null

    private var shapesRecycler: RecyclerView? = null
    private var imageItemList: MutableList<ImageItem>? = null
    private var adapter: ShapesAdapter? = null

    private var centerZoomLayoutManager: CenterZoomLayoutManager? = null

    private var previous: Button? = null
    private var play:Button? = null
    private var next:Button? = null
    private var counter = 0

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var sounds: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shapes)
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
            R.raw.circle,
            R.raw.triangle,
            R.raw.sphere,
            R.raw.square,
            R.raw.rectangle,
            R.raw.hexagon,
            R.raw.pentagon,
            R.raw.cylinder,
            R.raw.cube,
            R.raw.pyramid,
            R.raw.cone
        )

        imageItemList = ArrayList()
        initList()
        adapter = ShapesAdapter(this, imageItemList)

        shapesRecycler = findViewById<View>(R.id.recycler_shapes) as RecyclerView
        centerZoomLayoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        shapesRecycler!!.layoutManager = centerZoomLayoutManager
        shapesRecycler!!.itemAnimator = DefaultItemAnimator()
        shapesRecycler!!.adapter = adapter

        previous = findViewById<View>(R.id.previous_shapes) as Button
        play = findViewById<View>(R.id.play_shapes) as Button
        next = findViewById<View>(R.id.next_shapes) as Button

        counter = Int.MAX_VALUE / 2

        previous!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter--
            shapesRecycler!!.smoothScrollToPosition(counter)
        }

        next!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter++
            shapesRecycler!!.smoothScrollToPosition(counter)
        }

        shapesRecycler!!.scrollToPosition(counter)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(shapesRecycler)

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
        imageItemList!!.add(ImageItem("Circle", R.drawable.circle))
        imageItemList!!.add(ImageItem("Triangle", R.drawable.triangle))
        imageItemList!!.add(ImageItem("Sphere", R.drawable.sphere))
        imageItemList!!.add(ImageItem("Square", R.drawable.square))
        imageItemList!!.add(ImageItem("Rectangle", R.drawable.rectangle))
        imageItemList!!.add(ImageItem("Hexagon", R.drawable.hexagon))
        imageItemList!!.add(ImageItem("Pentagon", R.drawable.pentagon))
        imageItemList!!.add(ImageItem("Cylinder", R.drawable.cylinder))
        imageItemList!!.add(ImageItem("Cube", R.drawable.cube))
        imageItemList!!.add(ImageItem("Pyramid", R.drawable.pyramid))
        imageItemList!!.add(ImageItem("Cone", R.drawable.cone))
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