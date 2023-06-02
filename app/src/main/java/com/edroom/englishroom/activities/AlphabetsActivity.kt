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
import com.edroom.englishroom.adapters.AlphabetAdapter
import com.edroom.englishroom.helper.AdController
import com.edroom.englishroom.helper.AlphabetItem
import com.edroom.englishroom.helper.CenterZoomLayoutManager

class AlphabetsActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var container: LinearLayout? = null

    private var alphabetsList: MutableList<AlphabetItem>? = null
    private var alphabetRecycler: RecyclerView? = null
    private var adapter: AlphabetAdapter? = null
    private var centerZoomLayoutManager: CenterZoomLayoutManager? = null

    private var previous: Button? = null
    private var play: Button? = null
    private var next: Button? = null
    private var counter = 0

    private lateinit var sounds: IntArray
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alphabets)
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
            R.raw.p,
            R.raw.q,
            R.raw.r,
            R.raw.s,
            R.raw.t,
            R.raw.u,
            R.raw.v,
            R.raw.w,
            R.raw.x,
            R.raw.y,
            R.raw.z,
            R.raw.a,
            R.raw.b,
            R.raw.c,
            R.raw.d,
            R.raw.e,
            R.raw.f,
            R.raw.g,
            R.raw.h,
            R.raw.i,
            R.raw.j,
            R.raw.k,
            R.raw.l,
            R.raw.m,
            R.raw.n,
            R.raw.o
        )

        alphabetsList = ArrayList()
        initList()
        adapter = AlphabetAdapter(this, alphabetsList)
        centerZoomLayoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        alphabetRecycler = findViewById<View>(R.id.recyclerViewAlphabets) as RecyclerView
        previous = findViewById<View>(R.id.previous_alphabets) as Button
        play = findViewById<View>(R.id.play_alphabets) as Button
        next = findViewById<View>(R.id.next_alphabets) as Button

        alphabetRecycler!!.setLayoutManager(centerZoomLayoutManager)
        alphabetRecycler!!.setItemAnimator(DefaultItemAnimator())
        alphabetRecycler!!.setAdapter(adapter)

        counter = Int.MAX_VALUE / 2

        previous!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter--
            alphabetRecycler!!.smoothScrollToPosition(counter)
        }

        next!!.setOnClickListener(View.OnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            counter++
            alphabetRecycler!!.smoothScrollToPosition(counter)
        })

        alphabetRecycler!!.scrollToPosition(counter)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(alphabetRecycler)

        play!!.setOnClickListener {
            counter = centerZoomLayoutManager!!.findLastCompletelyVisibleItemPosition()
            val pos: Int = counter % (alphabetsList as ArrayList<AlphabetItem>).size
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer.create(applicationContext, sounds.get(pos))
            mediaPlayer!!.start()
        }

    }

    private fun initList() {
        alphabetsList!!.add(AlphabetItem("P p"))
        alphabetsList!!.add(AlphabetItem("Q q"))
        alphabetsList!!.add(AlphabetItem("R r"))
        alphabetsList!!.add(AlphabetItem("S s"))
        alphabetsList!!.add(AlphabetItem("T t"))
        alphabetsList!!.add(AlphabetItem("U u"))
        alphabetsList!!.add(AlphabetItem("V v"))
        alphabetsList!!.add(AlphabetItem("W w"))
        alphabetsList!!.add(AlphabetItem("X x"))
        alphabetsList!!.add(AlphabetItem("Y y"))
        alphabetsList!!.add(AlphabetItem("Z z"))
        alphabetsList!!.add(AlphabetItem("A a"))
        alphabetsList!!.add(AlphabetItem("B b"))
        alphabetsList!!.add(AlphabetItem("C c"))
        alphabetsList!!.add(AlphabetItem("D d"))
        alphabetsList!!.add(AlphabetItem("E e"))
        alphabetsList!!.add(AlphabetItem("F f"))
        alphabetsList!!.add(AlphabetItem("G g"))
        alphabetsList!!.add(AlphabetItem("H h"))
        alphabetsList!!.add(AlphabetItem("I i"))
        alphabetsList!!.add(AlphabetItem("J j"))
        alphabetsList!!.add(AlphabetItem("K k"))
        alphabetsList!!.add(AlphabetItem("L l"))
        alphabetsList!!.add(AlphabetItem("M m"))
        alphabetsList!!.add(AlphabetItem("N n"))
        alphabetsList!!.add(AlphabetItem("O o"))
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