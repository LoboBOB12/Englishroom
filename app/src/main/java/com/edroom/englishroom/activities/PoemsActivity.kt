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

class PoemsActivity : AppCompatActivity() {

    private var activity: Activity? = null
    private var backBtn: ImageView? = null
    private var mIntent: Intent? = null
    private var nativeAd_FL_1: FrameLayout? = null

    private var goingToSchoolPoem: LinearLayout? = null
    private var newSchoolPoem: LinearLayout? = null
    private var circleOfFriendsPoem: LinearLayout? = null
    private var dreamsPoem: LinearLayout? = null
    private var whatIsATreePoem: LinearLayout? = null
    private var beingHealthyPoem: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poems)
        activity = this

        initViews()
        contentViews()
    }

    private fun initViews() {
        backBtn = findViewById(R.id.backBtn)
        nativeAd_FL_1 = findViewById(R.id.nativeAd_FL_1)
        goingToSchoolPoem = findViewById(R.id.goingToSchoolPoem)
        newSchoolPoem = findViewById(R.id.newSchoolPoem)
        circleOfFriendsPoem = findViewById(R.id.circleOfFriendsPoem)
        dreamsPoem = findViewById(R.id.dreamsPoem)
        whatIsATreePoem = findViewById(R.id.whatIsATreePoem)
        beingHealthyPoem = findViewById(R.id.beingHealthyPoem)
    }

    private fun contentViews() {
        backBtn!!.setOnClickListener {
            onBackPressed()
        }

        /*Admob*/
        AdController.loadNativeAd(activity, nativeAd_FL_1)
        AdController.loadInterAd(activity)

        goingToSchoolPoem!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "Going To School")
            mIntent!!.putExtra("lyrics", "I came to school on a zebra today\n" +
                    "And my friends all laughed at me.\n" +
                    "It must have looked quite funny\n" +
                    "But my zebra ride was free.\n\n" +

                    "My friend arrived on a tricycle\n" +
                    "She thought she might be late.\n" +
                    "But her legs were strong from cycling\n" +
                    "And she whizzed straight through the gate.\n\n" +

                    "Another came in a hot air balloon\n" +
                    "He was the earliest to arrive.\n" +
                    "He’d floated above a traffic jam\n" +
                    "Of cars which had to drive.\n\n" +

                    "Next came the children on the bus\n" +
                    "Who were talking all together.\n" +
                    "The bus arrives on time each day\n" +
                    "In every kind of weather.\n\n" +

                    "My best friend walked, as he always does\n" +
                    "At the same time every day.\n" +
                    "He met some others on the path\n" +
                    "And they chatted all the way.\n\n" +

                    "Last were the children who came by car\n" +
                    "Arriving one by one.\n" +
                    "It took a long time and the cars in the queue\n" +
                    "All let their engines run.\n\n" +

                    "There are so many ways to get to school\n" +
                    "And shops and parks for fun.\n" +
                    "But the ways that are free and healthy\n" +
                    "Are the best for everyone")
            mIntent!!.putExtra("songAudio", R.raw.going_to_school)
            startActivity(mIntent)
        }

        newSchoolPoem!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "New School")
            mIntent!!.putExtra("lyrics", "It all looks quite familiar\n" +
                    "But it’s different all the same.\n" +
                    "I know that she’s my teacher\n" +
                    "But I still don’t know her name.\n\n" +

                    "I know I should remember\n" +
                    "But I found it hard to say.\n" +
                    "I hope I get my classmates’\n" +
                    "Names correct another day.\n\n" +

                    "Playtime’s in the playground\n" +
                    "But I don’t know where that is.\n" +
                    "And after that I’ve got PE\n" +
                    "And then a science quiz.\n\n" +

                    "I hope I packed my T-shirt\n" +
                    "And my running shoes look cool.\n" +
                    "There is so much to remember\n" +
                    "When you start a different school.\n\n" +

                    "My feet just are not used to\n" +
                    "Walking round a different place.\n" +
                    "And in English, maths and history\n" +
                    "Yet another different face.\n\n" +

                    "This new shirt feels so itchy\n" +
                    "And my face feels really hot.\n" +
                    "I wonder if they’ll like me?\n" +
                    "Of course they will, why not?\n\n" +

                    "And that kid there looks friendly\n" +
                    "That boy who’s wearing red.\n" +
                    "I think his name is Peter\n" +
                    "That’s what those girls just said.\n\n" +

                    "I know I should go over\n" +
                    "And say a shy hello.\n" +
                    "And if I’m lucky he will smile\n" +
                    "And show me where to go.\n\n" +

                    "But look, he’s coming over\n" +
                    "With a big smile on his face.\n" +
                    "It only takes one person\n" +
                    "To help you find your place.")
            mIntent!!.putExtra("songAudio", R.raw.new_school)
            startActivity(mIntent)
        }

        circleOfFriendsPoem!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "Circle of Friends")
            mIntent!!.putExtra("lyrics", "Why do we call it a circle of friends?\n" +
                    "The reason is it always bends.\n" +
                    "The shape won’t change with someone new\n" +
                    "If there are many or just a few.\n\n" +

                    "A circle is strong, it can shrink or grow\n" +
                    "As you meet new people you’d like to know\n" +
                    "Or lose a friend in a silly fight\n" +
                    "There are others there; it will be all right.\n\n" +

                    "A circle never runs out of space\n" +
                    "Come and go, there’ll be a place.\n" +
                    "A gap can close and open again\n" +
                    "To be filled by you, no matter when.\n\n" +

                    "There’s always room for another one\n" +
                    "Another person to share the fun\n" +
                    "Another person who you can share\n" +
                    "Your feelings with, who will always care.\n\n" +

                    "Inside the circle, the space is free\n" +
                    "To play with one or two or three.\n" +
                    "Skates and scooters, books and toys\n" +
                    "To share with different girls and boys.\n\n" +

                    "Other shapes have angles and lines\n" +
                    "You really can’t change those designs.\n" +
                    "Bend like the circle, make it wide\n" +
                    "You’ll find a world of friends inside.")
            mIntent!!.putExtra("songAudio", R.raw.circle_of_friends)
            startActivity(mIntent)
        }

        dreamsPoem!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "Dreams")
            mIntent!!.putExtra("lyrics", "Last night I flew in outer space\n" +
                    "I flew so fast and far.\n" +
                    "An alien spaceship tried to chase\n" +
                    "Me round a shining star.\n\n" +

                    "Last night I had a pair of wings\n" +
                    "But still I couldn’t fly.\n" +
                    "I had to watch as other things\n" +
                    "Like chairs went floating by!\n\n" +

                    "Last night I helped a dragon with\n" +
                    "A roar that had no fire.\n" +
                    "I gave it chilli peppers\n" +
                    "And watched the flames shoot higher.\n\n" +

                    "Last night I found some magic shoes\n" +
                    "Which made me feel so tall.\n" +
                    "I jumped so high I couldn’t lose\n" +
                    "When I played basketball.\n\n" +

                    "Last night I painted my whole town\n" +
                    "In yellow, blue and red.\n" +
                    "It looked as if a circus clown\n" +
                    "Had coloured it instead.\n\n" +

                    "Each night I curl up in my bed\n" +
                    "Excited to find out\n" +
                    "What I will be inside my head\n" +
                    "What my dreams will be about.")
            mIntent!!.putExtra("songAudio", R.raw.dreams)
            startActivity(mIntent)
        }

        whatIsATreePoem!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "What is a Tree?")
            mIntent!!.putExtra("lyrics", "What is a tree?\n" +
                    "A place to play, a wall to climb, a thing to swing.\n" +
                    "What is a tree?\n" +
                    "Adventures start when it can be anything.\n\n" +

                    "What is a bed?\n" +
                    "A place to sleep, a land to dream, mind wandering.\n" +
                    "What is a bed?\n" +
                    "Adventures start when it can be anything.\n\n" +

                    "What is a hall?\n" +
                    "A track to race, a lane to bowl, a stage to sing.\n" +
                    "What is a hall?\n" +
                    "Adventures start when it can be anything.\n\n" +

                    "What is a box?\n" +
                    "A place to hide, a hat to wear, treasure glittering.\n" +
                    "What is a box?\n" +
                    "Adventures start when it can be anything.\n\n" +

                    "What is that?\n" +
                    "It’s up to you, you’re in charge, the queen or king.\n" +
                    "What is that?\n" +
                    "Adventures start when it can be anything.")
            mIntent!!.putExtra("songAudio", R.raw.what_is_a_tree)
            startActivity(mIntent)
        }

        beingHealthyPoem!!.setOnClickListener {
            mIntent = Intent(this, PlayerActivity::class.java)
            mIntent!!.putExtra("songTitle", "Being Healthy")
            mIntent!!.putExtra("lyrics", "Mum says I have to brush my teeth\n" +
                    "In the morning and at night.\n" +
                    "They look and taste much better\n" +
                    "But I hate it when she’s right.\n\n" +

                    "Dad says I have to take a bath\n" +
                    "And wash my body well.\n" +
                    "I do feel nice and clean and fresh\n" +
                    "But now he’s right as well!\n\n" +

                    "My brother says I have to wash\n" +
                    "My hands to keep them clean.\n" +
                    "He’s right, my fingers feel so good\n" +
                    "Without dirt in between.\n\n" +

                    "My sister says I have to eat\n" +
                    "Some vegetables each day.\n" +
                    "She’s right, I have more energy\n" +
                    "To run and walk and play.\n\n" +

                    "I feel more healthy and I’ve got\n" +
                    "Much better teeth and skin.\n" +
                    "Don’t tell them that I think they’re right –\n" +
                    "I hate it when they win!")
            mIntent!!.putExtra("songAudio", R.raw.being_healthy)
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