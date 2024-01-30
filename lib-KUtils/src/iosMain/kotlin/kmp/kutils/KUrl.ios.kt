package kmp.kutils

import com.eygraber.uri.Uri
import platform.Foundation.NSURL

public fun NSURL.toUri(): Uri? = absoluteString?.let(Uri::parse)

public fun Uri.toNSURL(): NSURL? = NSURL.URLWithString(toString())