package com.morrisware.android.basic.devsupport

/**
 * Created by MorrisWare on 2018/8/2.
 * Email: MorrisWare01@gmail.com
 */
data class DevSupportSetting(
    var apiUrl: String?
) {
    companion object {
        const val KEY = "dev_support_setting_key"
    }
}