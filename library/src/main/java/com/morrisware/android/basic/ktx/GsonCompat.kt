package com.morrisware.android.basic.ktx

import com.google.gson.reflect.TypeToken

/**
 * 获取泛型type
 */
inline fun <reified T> genericType() = object : TypeToken<T>() {}.type