package kmp.kutils

import kmp.kutils.uri.Url
import org.w3c.dom.url.URL

public fun Url.toURL(): URL = URL(toString())

public fun Url.toURLOrNull(): URL? = runCatching { toURL() }.getOrNull()

public fun URL.toUrl(): Url = Url.parse(toString())

public fun URL.toUrlOrNull(): Url? = runCatching { toUrl() }.getOrNull()