package com.edroom.englishroom.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.edroom.englishroom.R

class Utils(private var context: Context) {

    companion object {

        fun shareApp(activity: Activity, appName: String) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBodyText =
                "https://play.google.com/store/apps/details?id=" + activity.packageName
            sharingIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                "Want your kids to learn & have fun? Try $appName. DOWNLOAD NOW!"
            )
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText)
            activity.startActivity(
                Intent.createChooser(
                    sharingIntent,
                    activity.resources.getString(R.string.share_via)
                )
            )
        }

        fun rateApp(activity: Activity) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + activity.packageName)
                )
            )
        }

        fun moreApps(activity: Activity) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + activity.packageName)
                )
            )
        }

        fun openUrl(activity: Activity, url: String) {
            if (url.isEmpty()) return
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            activity.startActivity(intent)
        }

    }

}