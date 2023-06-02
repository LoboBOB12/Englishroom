package com.edroom.englishroom

import android.app.Application
import com.edroom.englishroom.helper.AppOpenManager
import com.onesignal.OneSignal
import com.edroom.englishroom.data.Constants
import com.edroom.englishroom.helper.AdController

class MyApp : Application() {

    var appOpenManager: AppOpenManager? = null

    override fun onCreate() {
        super.onCreate()

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(Constants.ONESIGNAL_APP_ID)
        AdController.initAd(this)
        appOpenManager = AppOpenManager(this)
    }
}