package com.tz.log

import com.music.libase.base.BaseApp
import com.music.libase.config.LogConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger


@JvmDefaultWithoutCompatibility
interface TzLogConfig : LogConfig {

    override fun initLog() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BaseApp.app?.isDebug() ?: false
            }
        })
        Logger.addLogAdapter(DiskLogAdapter())
    }
}