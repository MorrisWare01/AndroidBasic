package com.morrisware.android.basic.ktx

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.Display

/**
 * Created by MorrisWare on 2018/8/10.
 * Email: MorrisWare01@gmail.com
 */
fun Context?.getScreenWidth(): Int = this?.resources?.displayMetrics?.widthPixels ?: 0

fun Context?.getScreenHeight(): Int = this?.resources?.displayMetrics?.heightPixels ?: 0

/**
 * 获得的高度包括屏幕高度和底部导航栏高度
 * getRealSize在API17以上才能调用
 */
fun Activity?.getFullScreenHeight(): Int = this?.run {
    val display = this.windowManager.defaultDisplay
    val realSize = Point()
    try {
        Display::class.java.getMethod("getRealSize", Point::class.java).invoke(
            display,
            realSize
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
    realSize.y
} ?: 0


