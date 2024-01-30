package kmp.kutils

import saschpe.kase64.base64Decoded
import saschpe.kase64.base64Encoded
import saschpe.kase64.base64UrlDecoded
import saschpe.kase64.base64UrlEncoded
import kotlin.jvm.JvmStatic




object KUtils {
    inline fun <reified T> tryCatchOrNull(block: () -> T, defaultValue: T? = null): T? = try {
        block()
    } catch (e: Exception) {
        defaultValue
    }
    inline fun <reified T> tryCatch(block: () -> T, defaultValue: T): T = tryCatchOrNull(block, defaultValue)!!

    @JvmStatic
    fun decode64(text: String) = tryCatchOrNull<String>(block = {text.base64Decoded}, "") ?: ""

    @JvmStatic
    fun encode64(text: String) = tryCatchOrNull<String>(block = {text.base64Encoded}, "") ?: ""

    @JvmStatic
    fun decodeSafe64(text: String) = tryCatchOrNull<String>(block = {text.base64UrlDecoded }, "") ?: ""

    @JvmStatic
    fun encodeSafe64(text: String) = tryCatchOrNull<String>(block = {text.base64UrlEncoded}, "") ?: ""



}

