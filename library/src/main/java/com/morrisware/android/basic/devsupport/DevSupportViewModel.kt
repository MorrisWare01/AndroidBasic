package com.morrisware.android.basic.devsupport

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.morrisware.android.basic.R
import com.morrisware.android.basic.ktx.toast

/**
 * Created by MorrisWare on 2018/8/2.
 * Email: MorrisWare01@gmail.com
 */
class DevSupportViewModel(application: Application) : AndroidViewModel(application) {

    private val devSupportRepository: DevSupportRepository = DevSupportRepository(application)
    private val devSupportSetting: DevSupportSetting

    val apiUrlLiveData = MutableLiveData<String>()
    val restartAppLiveData = MutableLiveData<Boolean>()
    val dismissLiveData = MutableLiveData<Boolean>()

    init {
        devSupportSetting = devSupportRepository.getDevSupportSetting()
        if (!devSupportSetting.apiUrl.isNullOrBlank()) {
            apiUrlLiveData.value = devSupportSetting.apiUrl
        }
    }

    fun saveApiUrl(apiUrl: String?) {
        if (apiUrl.isNullOrBlank()) {
            getApplication<Application>().toast(getApplication<Application>().getText(R.string.dev_support_api_url_empty))
            return
        }

        devSupportRepository.setDevSupportSetting(DevSupportSetting(apiUrl))
        getApplication<Application>().toast(getApplication<Application>().getText(R.string.dev_support_save_success))
    }

    fun restartApp() {
        restartAppLiveData.value = true
    }

    fun dismiss() {
        dismissLiveData.value = true
    }

}