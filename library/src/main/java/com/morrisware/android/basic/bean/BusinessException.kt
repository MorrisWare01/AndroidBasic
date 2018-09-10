package com.morrisware.android.basic.bean

data class BusinessException(
        override val message: String
) : Exception(message)
