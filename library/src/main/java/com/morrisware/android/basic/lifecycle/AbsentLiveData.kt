package com.morrisware.android.basic.lifecycle

import android.arch.lifecycle.LiveData

/**
 * 只会发送一次null值的LiveData，用于处理空数据
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): AbsentLiveData<T> {
            return AbsentLiveData()
        }
    }
}