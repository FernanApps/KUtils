package kmp.kutils

import kmp.kutils.uri.Uri
import platform.Foundation.NSURL

public fun NSURL.toUri(): Uri? = absoluteString?.let(Uri::parse)

public fun Uri.toNSURL(): NSURL? = NSURL.URLWithString(toString())