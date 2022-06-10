package com.music.libase.init

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.startup.Initializer
import com.blankj.utilcode.util.LogUtils
import com.music.libase.utils.PropertiesUtil


/**
 * @Author   leiwei
 * @Date     2022/5/12
 * @Desc
 */
class LibBaseInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        PropertiesUtil.init(context)

        val isRelease = PropertiesUtil.getValue("isRelease")
        LogUtils.getConfig().isLogSwitch = ("true" != isRelease)
        LogUtils.getConfig().globalTag = PropertiesUtil.getValue("globalTag")

        LogUtils.d(PropertiesUtil.getValue("baseUrl"))
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(OtherInitializer::class.java)
    }
}