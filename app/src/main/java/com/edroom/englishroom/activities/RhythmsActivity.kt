package com.edroom.englishroom.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.edroom.englishroom.R
import com.edroom.englishroom.helper.AdController

class RhythmsActivity : AppCompatActivity() {

    private var backBtn: ImageView? = null
    private var mIntent: Intent? = null
    private var activity: Activity? = null

    private var nativeAd_FL_1: FrameLayout? = null

    private var abcRhythm: LinearLayout? = null
    private var fiveLittleMonkeysRhythm: LinearLayout? = null
    private var rollYourBoatRhythm: LinearLayout? = null
    private var theWheelsOnTheBusRhythm: LinearLayout? = null
    private var aHuntingWeWillGoRhythm: LinearLayout? = null
    private var bingoRhythm: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rhythms)
        activity = this

        initViews()
        contentViews()
    }

    private fun initViews() {
        backBtn = findViewById(R.id.backBtn)
        abcRhythm = findViewById(R.id.abcRhythm)
        nativeAd_FL_1 = findViewById(R.id.nativeAd_FL_1)
        fiveLittleMonkeysRhythm = findViewById(R.id.fiveLittleMonkeysRhythm)
        rollYourBoatRhythm = findViewById(R.id.rollYourBoatRhythm)
        theWheelsOnTheBusRhythm = findViewById(R.id.theWheelsOnTheBusRhythm)
        aHuntingWeWillGoRhythm = findViewById(R.id.aHuntingWeWillGoRhythm)
        bingoRhythm = findViewById(R.id.bingoRhythm)
    }

    private fun contentViews() {
        backBtn!!.setOnClickListener {
            onBackPressed()
        }

        /*Admob*/
        AdController.loadNativeAd(activity, nativeAd_FL_1)
        AdController.loadInterAd(activity)

        abcRhythm!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "abc")
            mIntent!!.putExtra("lyrics", "A, B, C, D, E, F, G, H, I, J, K, L, M, N\n" +
                    "O, P, Q, R, S, T, U, V, W, X, Y, Z\n" +
                    "sing, sing I can sing\n" +
                    "sing, sing I can sing\n" +
                    "sing, sing I can sing\n" +
                    "ABC")
            mIntent!!.putExtra("songAudio", R.raw.abc)
            startActivity(mIntent)

        }

        fiveLittleMonkeysRhythm!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "5 Little Monkeys")
            mIntent!!.putExtra("lyrics", "5 Little Monkeys Jumping on the bed\n" +
                    "1 fell off and bumped its head\n" +
                    "Mama called the doctor and the doctor said,\n" +
                    "\"No more monkeys jumping on the bed!\"\n" +
                    "\n" +
                    "(Repeat with 4,3,2,1 Monkeys!)")
            mIntent!!.putExtra("songAudio", R.raw.five_little_monkeys)
            startActivity(mIntent)
        }

        rollYourBoatRhythm!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "Roll Your Boat")
            mIntent!!.putExtra("lyrics", "Row, row, row your boat, gently down the stream\n" +
                    "merrily, merrily, merrily, merrily, life is but a dream!")
            mIntent!!.putExtra("songAudio", R.raw.roll_your_boat)
            startActivity(mIntent)
        }

        theWheelsOnTheBusRhythm!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "The Wheels on The Bus")
            mIntent!!.putExtra("lyrics", "The wheels on the bus go round and round\n" +
                    "round and round, round and round\n" +
                    "the wheels on the bus go round and round\n" +
                    "all through the town\n" +
                    "The people on the bus go up and down\n" +
                    "up and down, up and down\n" +
                    "the people on the bus go up and down\n" +
                    "allthrough the town\n" +
                    "The wipers on the bus go swish,swish,swish\n" +
                    "swish,swish,swish,swish,swish,swish\n" +
                    "The wipers on the bus go swish,swish,swish\n" +
                    "all through the town\n" +
                    "The horn on the bus goes beep, beep, beep\n" +
                    "beep, beep, beep,beep, beep, beep\n" +
                    "The horn on the bus goes beep, beep, beep\n" +
                    "all through the town")
            mIntent!!.putExtra("songAudio", R.raw.the_wheels_on_the_bus)
            startActivity(mIntent)
        }

        aHuntingWeWillGoRhythm!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "A Hunting We Will Go")
            mIntent!!.putExtra("lyrics", "A hunting we will go, a hunting we will go\n" +
                    "We’ll catch a fox and put him in a box\n" +
                    "And then we’ll let him go\n" +
                    " \n" +
                    "A hunting we will go, a hunting we will go\n" +
                    "We’ll catch a cat and put him in a hat\n" +
                    "And then we’ll let him go\n" +
                    " \n" +
                    "A hunting we will go, a hunting we will go\n" +
                    "We’ll catch a snake and put him in a cake\n" +
                    "And then we’ll let him go\n" +
                    " \n" +
                    "A hunting we will go, a hunting we will go\n" +
                    "We’ll catch a goat and put him in a boat\n" +
                    "And then we’ll let him go\n" +
                    " \n" +
                    "A hunting we will go, a hunting we will go\n" +
                    "We’ll catch a mouse and put him in a house\n" +
                    "And then we’ll let him go\n" +
                    " \n" +
                    "A hunting we will go, a hunting we will go\n" +
                    "We’ll just pretend, and in the end\n" +
                    "We’ll always let them go!")
            mIntent!!.putExtra("songAudio", R.raw.a_hunting_we_will_go)
            startActivity(mIntent)
        }

        bingoRhythm!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "BINGO")
            mIntent!!.putExtra("lyrics", "There was a farmer had a dog and\n" +
                    "Bingo was his name-o\n" +
                    "B-I-N-G-O, B-I-N-G-O, B-I-N-G-O and\n" +
                    "BINGO was his name-o!\n" +
                    "There was a farmer had a dog and\n" +
                    "Bingo was his name-o\n" +
                    "-I-N-G-O, -I-N-G-O, -I-N-G-O and\n" +
                    "BINGO was his name-o!\n" +
                    "There was a farmer had a dog and\n" +
                    "Bingo was his name-o\n" +
                    "- -N-G-O, - -N-G-O, - -N-G-O and\n" +
                    "BINGO was his name-o!\n" +
                    "There was a farmer had a dog and\n" +
                    "Bingo was his name-o\n" +
                    "- - -G-O, - - -G-O, - - -G-O and\n" +
                    "BINGO was his name-o!\n" +
                    "There was a farmer had a dog and\n" +
                    "Bingo was his name-o\n" +
                    "- - - -O, - - - -O, - - - -O and\n" +
                    "BINGO was his name-o!\n" +
                    "There was a farmer had a dog and\n" +
                    "Bingo was his name-o\n" +
                    "- - - - , - - - - , - - - - and\n" +
                    "BINGO was his name-o!\n" +
                    "There was a farmer had a dog and\n" +
                    "Bingo was his name-o\n" +
                    "B-I-N-G-O, B-I-N-G-O, B-I-N-G-O and\n" +
                    "BINGO was his name-o!")
            mIntent!!.putExtra("songAudio", R.raw.bingo)
            startActivity(mIntent)
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