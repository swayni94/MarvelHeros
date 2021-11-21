package com.example.marvel.data.constant

import java.security.NoSuchAlgorithmException

const val baseUrl: String = "https://gateway.marvel.com:443/v1/public/"
const val apikey : String ="f06041ba31f5cc8d107edf1f41b7491f"
const val privateApiKey: String = "ebef3c1fc433bba1d74b292ff80c29c66744d6a1"
object Database {
    const val NAME = "character.db"
}

fun String.md5(): String {
    try {
        val digest = java.security.MessageDigest.getInstance("MD5")
        digest.update(toByteArray())
        val messageDigest = digest.digest()
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}