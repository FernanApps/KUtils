package kmp.kutils

enum class PlatformTarget {
    Android, Jvm, Js, IOs
}

expect val KurrentPlatformTarget: PlatformTarget