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

class VegetablesActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var container: LinearLayout? = null

    private var vegetablesRecycler: RecyclerView? = null
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
        setContentView(R.layout.activity_vegetables)
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
            R.raw.artichoke,
            R.raw.asparagus,
            R.raw.beans,
            R.raw.beetroot,
            R.raw.blue_cabbage,
            R.raw.broccoli,
            R.raw.brussel_sprouts,
            R.raw.carrot,
            R.raw.cauliflower,
            R.raw.celery,
            R.raw.chili_peppers,
            R.raw.chinese_cabbage,
            R.raw.corn,
            R.raw.cucumbers,
            R.raw.dil,
            R.raw.eggplant,
            R.raw.garlic,
            R.raw.green_cabbage,
            R.raw.lemon,
            R.raw.lettuce,
            R.raw.onion,
            R.raw.parsley,
            R.raw.peppers,
            R.raw.potatoe,
            R.raw.radish,
            R.raw.red_cabbage,
            R.raw.squash,
            R.raw.tomatoe,
            R.raw.zuchinni
        )

        imageItemList = ArrayList()
        initList()
        adapter = ImageAdapter(this, imageItemList)

        vegetablesRecycler = findViewById<View>(R.id.recycler_vegetables) as RecyclerView
        centerZoomLayoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        vegetablesRecycler!!.layoutManager = centerZoomLayoutManager
        vegetablesRecycler!!.itemAnimator = DefaultItemAnimator()
        vegetablesRecycler!!.adapter = adapter

        previous = findViewById<View>(R.id.previous_vegetables) as Button
        play = findViewById<View>(R.id.play_vegetables) as Button
        next = findViewById<View>(R.id.next_vegetables) as Button

        counter = Int.MAX_VALUE / 2

        previous!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter--
            vegetablesRecycler!!.smoothScrollToPosition(counter)
        }

        next!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter++
            vegetablesRecycler!!.smoothScrollToPosition(counter)
        }

        vegetablesRecycler!!.scrollToPosition(counter)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(vegetablesRecycler)

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
        imageItemList!!.add(ImageItem("Artichokes", R.drawable.artichokes))
        imageItemList!!.add(ImageItem("Asparagus", R.drawable.asparagus))
        imageItemList!!.add(ImageItem("Beans & Peas", R.drawable.beansandpeas))
        imageItemList!!.add(ImageItem("Beetroot", R.drawable.beet))
        imageItemList!!.add(ImageItem("Blue Cabbage", R.drawable.bluecabbage))
        imageItemList!!.add(ImageItem("Broccoli", R.drawable.broccolli))
        imageItemList!!.add(ImageItem("Brussels Sprouts", R.drawable.brusselssprout))
        imageItemList!!.add(ImageItem("Carrot", R.drawable.carrot))
        imageItemList!!.add(ImageItem("Cauliflower", R.drawable.cauli))
        imageItemList!!.add(ImageItem("Celery", R.drawable.celery))
        imageItemList!!.add(ImageItem("Chili Peppers", R.drawable.chilipeppers))
        imageItemList!!.add(ImageItem("Chinese Cabbage", R.drawable.chinesecabbage))
        imageItemList!!.add(ImageItem("Corn", R.drawable.corn))
        imageItemList!!.add(ImageItem("Cucumbers", R.drawable.cucumbers))
        imageItemList!!.add(ImageItem("Dil", R.drawable.dil))
        imageItemList!!.add(ImageItem("Eggplant", R.drawable.eggplant))
        imageItemList!!.add(ImageItem("Garlic", R.drawable.garlic))
        imageItemList!!.add(ImageItem("Green Cabbage", R.drawable.greencabbage))
        imageItemList!!.add(ImageItem("Lemon", R.drawable.lemon))
        imageItemList!!.add(ImageItem("Lettuce", R.drawable.lettuce))
        imageItemList!!.add(ImageItem("Onions", R.drawable.onions))
        imageItemList!!.add(ImageItem("Parsley", R.drawable.parseley))
        imageItemList!!.add(ImageItem("Peppers", R.drawable.peppers))
        imageItemList!!.add(ImageItem("Potatoes", R.drawable.potatoes))
        imageItemList!!.add(ImageItem("Radish", R.drawable.radish))
        imageItemList!!.add(ImageItem("Red Cabbage", R.drawable.redcabbage))
        imageItemList!!.add(ImageItem("Squashes", R.drawable.squashes))
        imageItemList!!.add(ImageItem("Tomatoes", R.drawable.tomatoes))
        imageItemList!!.add(ImageItem("Zuccini", R.drawable.zuccini))
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