package com.morrisware.android.basic.ktx

import android.app.Application
import android.content.Intent

/**
 * Created by MorrisWare on 2018/8/2.
 * Email: MorrisWare01@gmail.com
 */
fun Application.restartApp() {
    val intent: Intent = this.packageManager.getLaunchIntentForPackage(this.packageName)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
}