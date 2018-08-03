package com.morrisware.android.basic.devsupport

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.core.widget.toast
import com.google.gson.Gson
import com.morrisware.android.basic.R

/**
 * Created by MorrisWare on 2018/8/2.
 * Email: MorrisWare01@gmail.com
 */
class DevSupportViewModel(application: Application) : AndroidViewModel(application) {

    val apiUrlLiveData = MutableLiveData<String>()
    val restartAppLiveData = MutableLiveData<Boolean>()
    val dismissLiveData = MutableLiveData<Boolean>()

    private val sharedPreferences: SharedPreferences = getApplication<Application>()
        .getSharedPreferences("devSupport", Context.MODE_PRIVATE)
    private val gson: Gson = Gson()
    private val devSupportSetting: DevSupportSetting

    init {
        devSupportSetting = gson.fromJson<DevSupportSetting>(
            sharedPreferences.getString(DevSupportSetting.KEY, "{}"),
            DevSupportSetting::class.java)
        if (!devSupportSetting.apiUrl.isNullOrBlank()) {
            apiUrlLiveData.value = devSupportSetting.apiUrl
        }
    }

    fun saveApiUrl(apiUrl: String?) {
        if (apiUrl.isNullOrBlank()) {
            getApplication<Application>().toast(getApplication<Application>().getText(R.string.dev_support_api_url_empty))
            return
        }

        devSupportSetting.apiUrl = apiUrl
        sharedPreferences.edit {
            this.putString(DevSupportSetting.KEY, gson.toJson(devSupportSetting))
            this.apply()
        }
        getApplication<Application>().toast(getApplication<Application>().getText(R.string.dev_support_save_success))
    }

    fun restartApp() {
        restartAppLiveData.value = true
    }

    fun dismiss() {
        dismissLiveData.value = true
    }

}