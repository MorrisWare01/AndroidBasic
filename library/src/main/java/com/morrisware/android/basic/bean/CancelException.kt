package com.morrisware.android.basic.bean

/**
 * Created by MorrisWare on 2018/9/10.
 * Email: MorrisWare01@gmail.com
 */
class CancelException(
    override val message: String
) : Exception(message)