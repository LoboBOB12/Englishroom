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
import com.google.android.gms.ads.AdView
import com.edroom.englishroom.R
import com.edroom.englishroom.adapters.SpellingAdapter
import com.edroom.englishroom.helper.AdController
import com.edroom.englishroom.helper.AlphabetItem
import com.edroom.englishroom.helper.CenterZoomLayoutManager

class SpellingsActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var container: LinearLayout? = null

    private var spellingsRecycler: RecyclerView? = null
    private var alphabetItemList: MutableList<AlphabetItem>? = null
    private var adapter: SpellingAdapter? = null

    private var centerZoomLayoutManager: CenterZoomLayoutManager? = null

    private var previous: Button? = null
    private var play:Button? = null
    private var next:Button? = null
    private var counter = 0

    private var adView: AdView? = null

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var sounds: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spellings)
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
            R.raw.all, R.raw.bat, R.raw.cat, R.raw.dad, R.raw.dog, R.raw.hen, R.raw.hello,
            R.raw.mom, R.raw.one, R.raw.papa, R.raw.pet, R.raw.rat, R.raw.sun, R.raw.toy, R.raw.yes
        )
        alphabetItemList = ArrayList()
        initList()
        adapter = SpellingAdapter(this, alphabetItemList)

        spellingsRecycler = findViewById<View>(R.id.recycler_spellings) as RecyclerView
        centerZoomLayoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        spellingsRecycler!!.layoutManager = centerZoomLayoutManager
        spellingsRecycler!!.itemAnimator = DefaultItemAnimator()
        spellingsRecycler!!.adapter = adapter

        previous = findViewById<View>(R.id.previous_spellings) as Button
        play = findViewById<View>(R.id.play_spellings) as Button
        next = findViewById<View>(R.id.next_spellings) as Button

        counter = Int.MAX_VALUE / 2

        previous!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter--
            spellingsRecycler!!.smoothScrollToPosition(counter)
        }

        next!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter++
            spellingsRecycler!!.smoothScrollToPosition(counter)
        }

        spellingsRecycler!!.scrollToPosition(counter)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(spellingsRecycler)

        play!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            val pos = counter % (alphabetItemList as ArrayList<AlphabetItem>).size
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer.create(applicationContext, sounds[pos])
            mediaPlayer!!.start()
            adapter!!.notifyDataSetChanged()
        }

    }

    private fun initList() {
        alphabetItemList!!.add(AlphabetItem("All"))
        alphabetItemList!!.add(AlphabetItem("Bat"))
        alphabetItemList!!.add(AlphabetItem("Cat"))
        alphabetItemList!!.add(AlphabetItem("Dad"))
        alphabetItemList!!.add(AlphabetItem("Dog"))
        alphabetItemList!!.add(AlphabetItem("Hen"))
        alphabetItemList!!.add(AlphabetItem("Hello"))
        alphabetItemList!!.add(AlphabetItem("Mom"))
        alphabetItemList!!.add(AlphabetItem("One"))
        alphabetItemList!!.add(AlphabetItem("Papa"))
        alphabetItemList!!.add(AlphabetItem("Pet"))
        alphabetItemList!!.add(AlphabetItem("Rat"))
        alphabetItemList!!.add(AlphabetItem("Sun"))
        alphabetItemList!!.add(AlphabetItem("Toy"))
        alphabetItemList!!.add(AlphabetItem("Yes"))
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