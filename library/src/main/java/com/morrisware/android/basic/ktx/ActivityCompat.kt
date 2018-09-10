package com.morrisware.android.basic.ktx

import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.view.Display
import android.view.WindowManager

/**
 * Created by MorrisWare on 2018/9/6.
 * Email: MorrisWare01@gmail.com
 */

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

/**
 * 显示隐藏状态栏
 */
fun Activity?.setFullScreen(enable: Boolean) {
    this?.apply {
        if (enable) {
            val attributes = window.attributes
            attributes.flags = attributes.flags and WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN.inv()
            window.setFlags(attributes.flags, -1)
        } else {
            val attrs = window.attributes
            attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
            window.setFlags(attrs.flags, -1)
        }
    }
}

fun Activity.restartApp() {
    val packageManager = packageManager
    val intent = packageManager.getLaunchIntentForPackage(packageName) ?: return
    val componentName = intent.component
    val mainIntent = Intent.makeRestartActivityTask(componentName)
    startActivity(mainIntent)
    System.exit(0)
}

/**
 * 选择图片
 */
fun Activity.openGallery(requestCode: Int) {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*";
    startActivityForResult(intent, requestCode);
}
