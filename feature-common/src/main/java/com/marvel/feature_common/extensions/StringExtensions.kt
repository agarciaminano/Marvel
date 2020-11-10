package com.marvel.feature_common.extensions

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.md5(): String {
    var m: MessageDigest? = null
    try {
        m = MessageDigest.getInstance("MD5")
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    m?.update(toByteArray(), 0, length)
    return BigInteger(1, m?.digest()).toString(16)
}