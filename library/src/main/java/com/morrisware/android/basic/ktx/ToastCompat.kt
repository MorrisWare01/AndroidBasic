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
): android.widget.Toast {
    val toast = Toast.makeText(this.applicationContext, text, duration)
    toast.show()
    return toast
}

fun Context.toast(
    resId: Int,
    duration: Int = Toast.LENGTH_SHORT
): android.widget.Toast {
    val toast = Toast.makeText(this.applicationContext, resId, duration)
    toast.show()
    return toast
}
