package com.morrisware.android.basic.developer

/**
 * Created by MorrisWare on 2018/8/2.
 * Email: MorrisWare01@gmail.com
 */
data class DeveloperSetting(
    var apiUrl: String?
) {
    companion object {
        const val DEVELOPER_KEY = "developer_key"
    }
}