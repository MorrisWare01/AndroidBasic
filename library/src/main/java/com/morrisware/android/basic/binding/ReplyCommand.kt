package com.morrisware.android.basic.binding

/**
 * Created by MorrisWare on 2018/8/9.
 * Email: MorrisWare01@gmail.com
 */
class ReplyCommand(
    private val callback: () -> Unit
) {
    fun run() {
        this.callback()
    }

    companion object {
        val EMPTY_COMMAND = ReplyCommand {}
    }
}
