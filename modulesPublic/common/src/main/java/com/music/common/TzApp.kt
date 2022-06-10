package com.tz.tzbaselib

import android.app.Application
import com.orhanobut.logger.BuildConfig
import com.tz.log.TzLogConfig
import com.tz.router.TzRouterConfig
import com.music.common.storage.TzSPConfig
import com.music.libase.base.BaseApp

@JvmDefaultWithoutCompatibility
interface TzApp:BaseApp,TzLogConfig, TzRouterConfig, TzSPConfig {

    override fun init(instance: BaseApp) {
        super.init(instance)
        initLog()
        initRouter(instance.ctx as Application)
        initSP(ctx)
    }

    override fun getToken(): String = "token"

    override fun getSPName(): String = "com.common.main"

    override fun displayWidth(): Float  = 375f

    override fun isDebug(): Boolean = BuildConfig.DEBUG
}