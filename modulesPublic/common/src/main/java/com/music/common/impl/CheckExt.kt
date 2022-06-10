package com.music.common.impl

fun String.checkCnEnNum(st: Int = 1, en: Int = 32): Boolean {
    return "^[a-zA-Z0-9\u4e00-\u9fa5]{$st,$en}$".toRegex().matches(this)
}

fun String.checkNum(st: Int = 0, en: Int = 32): Boolean {
    return "^[0-9]{$st,$en}$".toRegex().matches(this)
}

fun String.checkCnDot(st: Int = 0, en: Int = 32): Boolean {
    return "^[a-zA-Z\u4e00-\u9fa5]{$st,$en}$".toRegex().matches(this)
}