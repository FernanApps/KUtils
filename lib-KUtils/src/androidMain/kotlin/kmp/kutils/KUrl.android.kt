package kmp.kutils

import kmp.kutils.uri.Uri
import kmp.kutils.uri.Url
import android.net.Uri as AndroidUri

public fun Uri.toAndroidUri(): AndroidUri = AndroidUri.parse(toString())

public fun Uri.toAndroidUriOrNull(): AndroidUri? = runCatching { toAndroidUri() }.getOrNull()

public fun AndroidUri.toUri(): Uri = Uri.parse(toString())

public fun AndroidUri.toUriOrNull(): Uri? = runCatching { toUri() }.getOrNull()


public fun Url.toAndroidUri(): AndroidUri = AndroidUri.parse(toString())

public fun Url.toAndroidUriOrNull(): AndroidUri? = runCatching { toAndroidUri() }.getOrNull()

public fun AndroidUri.toUrl(): Url = Url.parse(toString())

public fun AndroidUri.toUrlOrNull(): Url? = runCatching { toUrl() }.getOrNull()