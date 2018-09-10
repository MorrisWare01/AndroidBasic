package com.morrisware.android.basic.ktx

import android.content.Context

/**
 * Created by MorrisWare on 2018/8/10.
 * Email: MorrisWare01@gmail.com
 */
fun Context?.getScreenWidth(): Int = this?.resources?.displayMetrics?.widthPixels ?: 0

fun Context?.getScreenHeight(): Int = this?.resources?.displayMetrics?.heightPixels ?: 0
