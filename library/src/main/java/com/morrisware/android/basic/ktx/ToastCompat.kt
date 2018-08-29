package com.morrisware.android.basic.ktx

import android.content.Context
import android.widget.Toast

/**
 * Created by MorrisWare on 2018/8/29.
 * Email: MorrisWare01@gmail.com
 */
fun Context.toast(
    text: CharSequence,
    duration: Int = Toast.LENGTH_SHORT
): android.widget.Toast = Toast.makeText(this, text, duration)

fun Context.toast(
    resId: Int,
    duration: Int = Toast.LENGTH_SHORT
): android.widget.Toast = Toast.makeText(this, resId, duration)
