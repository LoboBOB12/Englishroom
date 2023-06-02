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

class VehiclesActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var container: LinearLayout? = null

    private var vehiclesRecycler: RecyclerView? = null
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
        setContentView(R.layout.activity_vehicles)
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
            R.raw.aeroplane,
            R.raw.autorickshaw,
            R.raw.cab,
            R.raw.camping_car,
            R.raw.car,
            R.raw.caravan,
            R.raw.cargotruck,
            R.raw.dliveryvan,
            R.raw.hotairbaloon,
            R.raw.icecreamtruck,
            R.raw.motorcycle,
            R.raw.scooter,
            R.raw.ship,
            R.raw.speedboat,
            R.raw.truck
        )

        imageItemList = ArrayList()
        initList()
        adapter = ImageAdapter(this, imageItemList)

        vehiclesRecycler = findViewById<View>(R.id.recycler_vehicles) as RecyclerView
        centerZoomLayoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        vehiclesRecycler!!.layoutManager = centerZoomLayoutManager
        vehiclesRecycler!!.itemAnimator = DefaultItemAnimator()
        vehiclesRecycler!!.adapter = adapter

        previous = findViewById<View>(R.id.previous_vehicles) as Button
        play = findViewById<View>(R.id.play_vehicles) as Button
        next = findViewById<View>(R.id.next_vehicles) as Button

        counter = Int.MAX_VALUE / 2

        previous!!.setOnClickListener(View.OnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter--
            vehiclesRecycler!!.smoothScrollToPosition(counter)
        })

        next!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter++
            vehiclesRecycler!!.smoothScrollToPosition(counter)
        }

        vehiclesRecycler!!.scrollToPosition(counter)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(vehiclesRecycler)

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
        imageItemList!!.add(ImageItem("Aeroplane", R.drawable.aeroplane))
        imageItemList!!.add(ImageItem("Auto Rickshaw", R.drawable.auto))
        imageItemList!!.add(ImageItem("Cab", R.drawable.cab))
        imageItemList!!.add(ImageItem("Camping Car", R.drawable.campingcar))
        imageItemList!!.add(ImageItem("Car", R.drawable.car))
        imageItemList!!.add(ImageItem("Caravan", R.drawable.caravan))
        imageItemList!!.add(ImageItem("Cargo", R.drawable.cargo))
        imageItemList!!.add(ImageItem("Delivery Van", R.drawable.deliveryvan))
        imageItemList!!.add(ImageItem("Hot Air Balloon", R.drawable.hotairballoon))
        imageItemList!!.add(ImageItem("IceCream Truck", R.drawable.icecream))
        imageItemList!!.add(ImageItem("Motorcycle", R.drawable.motorcycle))
        imageItemList!!.add(ImageItem("Scooter", R.drawable.scooter))
        imageItemList!!.add(ImageItem("Ship", R.drawable.ship))
        imageItemList!!.add(ImageItem("Speed Boat", R.drawable.speedboat))
        imageItemList!!.add(ImageItem("Truck", R.drawable.truck))
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