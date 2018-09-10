package com.morrisware.android.basic.bean

import timber.log.Timber

/**
 * Created by MorrisWare on 2018/7/13.
 * Email: MorrisWare01@gmail.com
 */
enum class Status {
    SUCCESS,
    ERROR,
}

data class Resource<T>(
    val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null
) {

    val errCode: Int?
        get() {
            if (this.throwable is ServerException) {
                return this.throwable.code
            } else {
                return null
            }
        }

    val message: String
        get() {
            Timber.e("$throwable")
            if (this.throwable == null) {
                return "success"
            } else if (this.throwable is ServerException) {
                return this.throwable.message
            } else if (this.throwable is BusinessException) {
                return this.throwable.message
            } else if (this.throwable is CancelException) {
                return this.throwable.message
            } else {
                return "error"
            }
        }
}