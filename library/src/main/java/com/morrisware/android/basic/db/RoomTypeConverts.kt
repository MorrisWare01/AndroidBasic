package com.morrisware.android.basic.db

import com.google.gson.Gson
import com.morrisware.android.basic.ktx.genericType

open class RoomTypeConverts {

    protected val gson = Gson()

    protected fun <T> stringToList(data: String?): List<T>? = when (data) {
        null -> emptyList()
        else -> gson.fromJson<List<T>>(data, genericType<List<T>>())
    }

    protected fun <T> listToString(data: List<T>?): String? = gson.toJson(data)
}