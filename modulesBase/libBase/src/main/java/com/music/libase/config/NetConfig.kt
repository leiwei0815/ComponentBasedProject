package com.music.libase.config

@JvmDefaultWithoutCompatibility
interface NetConfig {

    fun getToken(): String

    fun getVersion(): String

    fun isDebug(): Boolean

}