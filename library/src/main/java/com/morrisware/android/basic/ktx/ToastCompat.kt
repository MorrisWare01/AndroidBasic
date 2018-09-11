package com.morrisware.android.basic.ktx

import android.content.Context
import android.widget.Toast

/**
 * Created by MorrisWare on 2018/8/29.
 * Email: MorrisWare01@gmail.com
 */
object ToastContainer {
    var toast: Toast? = null
}

fun Context.toast(
    text: CharSequence,
    duration: Int = Toast.LENGTH_SHORT
): android.widget.Toast {
    ToastContainer.toast?.cancel()
    val toast = Toast.makeText(this.applicationContext, text, duration)
    toast.show()
    ToastContainer.toast = toast
    return toast
}

fun Context.toast(
    resId: Int,
    duration: Int = Toast.LENGTH_SHORT
): android.widget.Toast {
    ToastContainer.toast?.cancel()
    val toast = Toast.makeText(this.applicationContext, resId, duration)
    toast.show()
    ToastContainer.toast = toast
    return toast
}
