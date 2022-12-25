package me.scribbble.backend.security

import java.security.MessageDigest

fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

fun sha256(origin: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(origin.toByteArray())
    return digest.toHex()
}
