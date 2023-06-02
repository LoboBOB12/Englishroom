package com.edroom.englishroom.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.infideap.drawerbehavior.Advance3DDrawerLayout
import com.edroom.englishroom.R
import com.edroom.englishroom.data.Constants
import com.edroom.englishroom.helper.AdController
import com.edroom.englishroom.utils.Utils

class MainActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backPressedTime: Long = 0
    private var appDrawerLayout: Advance3DDrawerLayout? = null
    private var appMenuBtn: ImageView? = null

    private var nativeAd_FL_1: FrameLayout? = null
    private var nativeAd_FL_2: FrameLayout? = null

    private var cardRhythms: CardView? = null
    private var cardPoems: CardView? = null
    private var cardAlphabets: CardView? = null
    private var cardAnimals: CardView? = null
    private var cardFruits: CardView? = null
    private var cardVegetables: CardView? = null
    private var cardVehicles: CardView? = null
    private var cardShapes: CardView? = null
    private var cardObjects: CardView? = null
    private var cardColors: CardView? = null
    private var cardSpellings: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity = this

        initViews()
        contentViews()
        appDrawer()
    }

    private fun initViews() {
        appMenuBtn = findViewById(R.id.appMenuBtn)
        appDrawerLayout = findViewById(R.id.appDrawerLayout)

        nativeAd_FL_1 = findViewById(R.id.nativeAd_FL_1)
        nativeAd_FL_2 = findViewById(R.id.nativeAd_FL_2)

        cardRhythms = findViewById(R.id.cardRhythms)
        cardPoems = findViewById(R.id.cardPoems)
        cardAlphabets = findViewById(R.id.cardAlphabets)
        cardAnimals = findViewById(R.id.cardAnimals)
        cardFruits = findViewById(R.id.cardFruits)
        cardVegetables = findViewById(R.id.cardVegetables)
        cardVehicles = findViewById(R.id.cardVehicles)
        cardShapes = findViewById(R.id.cardShapes)
        cardObjects = findViewById(R.id.cardObjects)
        cardColors = findViewById(R.id.cardColors)
        cardSpellings = findViewById(R.id.cardSpellings)
    }

    private fun contentViews() {
        appMenuBtn!!.setOnClickListener { view: View? -> openAppDrawer() }

        /*Admob*/
        AdController.loadNativeAd(activity, nativeAd_FL_1)
        AdController.loadNativeAd(activity, nativeAd_FL_2)
        AdController.loadInterAd(activity)

        cardRhythms!!.setOnClickListener {
            startActivity(Intent(this, RhythmsActivity::class.java))
        }
        cardPoems!!.setOnClickListener {
            startActivity(Intent(this, PoemsActivity::class.java))
        }
        cardAlphabets!!.setOnClickListener {
            startActivity(Intent(this, AlphabetsActivity::class.java))
        }
        cardAnimals!!.setOnClickListener {
            startActivity(Intent(this, AnimalsActivity::class.java))
        }
        cardFruits!!.setOnClickListener {
            startActivity(Intent(this, FruitsActivity::class.java))
        }
        cardVegetables!!.setOnClickListener {
            startActivity(Intent(this, VegetablesActivity::class.java))
        }
        cardVehicles!!.setOnClickListener {
            startActivity(Intent(this, VehiclesActivity::class.java))
        }
        cardShapes!!.setOnClickListener {
            startActivity(Intent(this, ShapesActivity::class.java))
        }
        cardObjects!!.setOnClickListener {
            startActivity(Intent(this, ObjectsActivity::class.java))
        }
        cardColors!!.setOnClickListener {
            startActivity(Intent(this, ColorsActivity::class.java))
        }
        cardSpellings!!.setOnClickListener {
            startActivity(Intent(this, SpellingsActivity::class.java))
        }
    }

    private fun appDrawer() {
        appDrawerLayout!!.setViewScale(GravityCompat.START, 0.9f)
        appDrawerLayout!!.setViewElevation(
            GravityCompat.START,
            30f
        )
        appDrawerLayout!!.setViewScrimColor(
            GravityCompat.START,
            ContextCompat.getColor(this, R.color.primary)
        )
        appDrawerLayout!!.drawerElevation = 30f
        appDrawerLayout!!.setRadius(GravityCompat.START, 25f)
        appDrawerLayout!!.setViewRotation(GravityCompat.START, 0f)
        appDrawerLayout!!.closeDrawer(GravityCompat.START)
        appDrawerLayout!!.addDrawerListener(object : DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerOpened(drawerView: View) {}
            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerStateChanged(newState: Int) {}
        })
        initAppDrawer()
    }

    private fun closeAppDrawer() {
        appDrawerLayout!!.closeDrawer(
            GravityCompat.START
        )
    }

    private fun openAppDrawer() {
        appDrawerLayout!!.openDrawer(
            GravityCompat.START
        )
    }

    private fun initAppDrawer() {
        findViewById<View>(R.id.navHomeBtn).setOnClickListener { view: View? -> closeAppDrawer() }
        findViewById<View>(R.id.navInAppPurchasesBtn).setOnClickListener { view: View? ->
            closeAppDrawer()
            startActivity(Intent(this@MainActivity, PremiumActivity::class.java))
        }
        findViewById<View>(R.id.navShareBtn).setOnClickListener { view: View? ->
            closeAppDrawer()
            Utils.shareApp(activity!!, resources.getString(R.string.app_name))
        }
        findViewById<View>(R.id.navRateBtn).setOnClickListener { view: View? ->
            closeAppDrawer()
            Utils.rateApp(activity!!)
        }
        findViewById<View>(R.id.navPrivacyPolicyBtn).setOnClickListener { view: View? ->
            closeAppDrawer()
            Utils.openUrl(activity!!, Constants.PRIVACY_POLICY_URL)
        }
        findViewById<View>(R.id.navAboutBtn).setOnClickListener { view: View? ->
            closeAppDrawer()
            startActivity(Intent(this@MainActivity, AboutActivity::class.java))
        }
        findViewById<View>(R.id.navMoreBtn).setOnClickListener { view: View? ->
            closeAppDrawer()
            Utils.moreApps(activity!!)
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(
                applicationContext,
                "Please click BACK again to exit",
                Toast.LENGTH_SHORT
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}