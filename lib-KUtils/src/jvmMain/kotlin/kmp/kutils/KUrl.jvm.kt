package kmp.kutils

import kmp.kutils.uri.Uri
import kmp.kutils.uri.Url
import kmp.kutils.uri.parts.Part
import kmp.kutils.uri.parts.PathPart
import kmp.kutils.uri.uris.HierarchicalUri
import java.io.File
import java.net.URI

public fun Uri.toURI(): URI = URI(toString())

public fun Uri.toURIOrNull(): URI? = runCatching { toURI() }.getOrNull()

public fun URI.toUri(): Uri = Uri.parse(toString())

public fun URI.toUriOrNull(): Uri? = runCatching { toUri() }.getOrNull()

/**
 * Creates a Uri from a file. The URI has the form
 * "file://<absolute path>". Encodes path characters with the exception of '/'.
 *
 * <p>Example: "file:///tmp/android.txt"
 *
 * @return a Uri for the given file
 */
public fun File.toUri(): Uri {
    val path = PathPart.fromDecoded(absolutePath)
    return HierarchicalUri("file", Part.EMPTY, path, Part.NULL, Part.NULL)
}

public fun Url.toURI(): URI = URI(toString())

public fun Url.toURIOrNull(): URI? = runCatching { toURI() }.getOrNull()

public fun URI.toUrl(): Url = Url.parse(toString())

public fun URI.toUrlOrNull(): Url? = runCatching { toUrl() }.getOrNull()