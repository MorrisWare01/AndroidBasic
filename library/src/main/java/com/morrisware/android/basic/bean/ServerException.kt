package com.morrisware.android.basic.bean

/**
 * Created by MorrisWare on 2018/7/13.
 * Email: MorrisWare01@gmail.com
 */
data class ServerException(
        val code: Int,
        override val message: String
) : Exception(message)