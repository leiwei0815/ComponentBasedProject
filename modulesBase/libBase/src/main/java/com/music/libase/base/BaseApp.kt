package com.music.libase.base

import android.content.Context
import com.music.libase.config.LogConfig
import com.music.libase.config.NetConfig
import com.music.libase.config.SPConfig
import com.music.libase.config.ScreenConfig
@JvmDefaultWithoutCompatibility
interface BaseApp: NetConfig,LogConfig,SPConfig, ScreenConfig {
    val ctx: Context

    companion object {
        var app: BaseApp? = null
    }

    fun init(instance:BaseApp){
        app=instance
    }
}