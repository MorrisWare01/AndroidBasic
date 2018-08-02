package com.morrisware.android.basic.developer

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.core.widget.toast
import com.google.gson.Gson

/**
 * Created by MorrisWare on 2018/8/2.
 * Email: MorrisWare01@gmail.com
 */
class DeveloperViewModel(
    application: Application,
    defaultUrl: String
) : AndroidViewModel(application) {

    val apiUrlLiveData = MutableLiveData<String>()
    val restartAppLiveData = MutableLiveData<Boolean>()
    val dismissLiveData = MutableLiveData<Boolean>()

    private val sharedPreferences: SharedPreferences = getApplication<Application>()
        .getSharedPreferences("developer", Context.MODE_PRIVATE)
    private val gson: Gson = Gson()
    private val developerSetting: DeveloperSetting

    init {
        developerSetting = gson.fromJson<DeveloperSetting>(
            sharedPreferences.getString(DeveloperSetting.DEVELOPER_KEY, "{}"),
            DeveloperSetting::class.java)
        if (developerSetting.apiUrl.isNullOrBlank()) {
            developerSetting.apiUrl = defaultUrl
        }
    }

    fun saveApiUrl(apiUrl: String) {
        if (apiUrl.isBlank()) {
            getApplication<Application>().toast("Api地址不能为空")
            return
        }

        developerSetting.apiUrl = apiUrl
        sharedPreferences.edit {
            this.putString(DeveloperSetting.DEVELOPER_KEY, gson.toJson(developerSetting))
            this.apply()
        }
        getApplication<Application>().toast("保存成功")
    }

    fun restartApp() {
        restartAppLiveData.value = true
    }

    fun dismiss() {
        dismissLiveData.value = true
    }


}