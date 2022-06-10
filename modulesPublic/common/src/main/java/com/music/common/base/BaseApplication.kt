package com.music.common.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.music.libase.utils.PropertiesUtil
import com.tz.tzbaselib.TzApp

/**
 * @Author   leiwei
 * @Date     2022/5/13
 * @Desc
 */
open class BaseApplication : Application(), TzApp {

    override fun onCreate() {
        super.onCreate()
        LogUtils.d("BaseApplication onCreate : ")
        val isRelease = PropertiesUtil.getValue("isRelease")
        if ("true" != isRelease) {
            ARouter.openDebug()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    override val ctx: Context
        get() = this

    override fun getVersion(): String =
        packageManager.getPackageInfo(packageName, 0).versionName

}