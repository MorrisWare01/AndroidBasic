package com.mumuwu.paradise.ktx

import android.support.v4.util.ArrayMap
import java.io.UnsupportedEncodingException
import java.net.URLDecoder

/**
 * Created by MorrisWare on 2018/8/9.
 * Email: MorrisWare01@gmail.com
 */
// 验证是不是手机号
fun String?.isPhone(): Boolean = this?.let { this.matches("^(1)\\d{10}$".toRegex()) } ?: false

// 验证是不是密码
fun String?.isPassword(): Boolean = this?.let {
    this.matches("^[a-zA-Z0-9_]{6,20}\$".toRegex())
} ?: false

// 验证是不是验证码
fun String?.isCaptcha(): Boolean = this?.let { this.matches("^\\d{4,6}$".toRegex()) } ?: false

fun String?.isUrl(): Boolean = this?.let {
    this.matches(("^(((file|gopher|news|nntp|telnet|http|ftp|https|ftps|sftp)://)|" +
        "(www\\.))+(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}" +
        "\\.[0-9]{1,3}\\.[0-9]{1,3}))(/[a-zA-Z0-9\\&%_\\./-~-]*)?\$").toRegex()
    )
} ?: false

fun String?.getUrlHost(): String = this?.let {
    if (it.isUrl())
        it.substring(0, it.indexOf("/", it.indexOf("//") + 2) + 1);
    else
        ""
} ?: ""

fun String?.parseURLGetParams(): Map<String, String> = this?.let { it ->
    val map = ArrayMap<String, String>()
    try {
        val decode = URLDecoder.decode(it, "utf-8")
        val split = decode.substring(decode.indexOf("?") + 1, decode.length)
            .split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var keyValue: Array<String>
        for (s in split) {
            keyValue = s.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (keyValue.size == 2) {
                map[keyValue[0]] = keyValue[1]
            }
        }
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    }
    map
} ?: ArrayMap<String, String>()

/**
 * 获取后缀
 */
fun String?.getExtension(): String = this?.let { it ->
    it.substring(it.lastIndexOf(".", it.length))
} ?: ""
