package com.tz.router

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.lxj.xpopup.BuildConfig
import com.music.libase.config.RouterConfig

/**
 * @auther 子非猿
 * @date 2021/10/07
 * @Description
 */
interface TzRouterConfig:RouterConfig {
    override fun initRouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }
}