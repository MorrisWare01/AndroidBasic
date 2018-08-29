package com.morrisware.android.basic.devsupport

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class DevSupportRepository constructor(application: Application) {

    private val gson: Gson = Gson()
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("devSupport", Context.MODE_PRIVATE)

    fun getDevSupportSetting(): DevSupportSetting {
        return gson.fromJson<DevSupportSetting>(sharedPreferences.getString(
            DevSupportSetting.KEY, "{}"), DevSupportSetting::class.java)
    }

    fun setDevSupportSetting(devSupportSetting: DevSupportSetting) {
        sharedPreferences.edit().apply {
            this.putString(DevSupportSetting.KEY, gson.toJson(devSupportSetting))
            this.apply()
        }
    }

}