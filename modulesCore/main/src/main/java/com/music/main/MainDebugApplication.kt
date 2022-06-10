package com.music.main

import android.app.Application
import com.music.common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * @Author   leiwei
 * @Date     2022/5/16
 * @Desc     debug单独调试运行的application
 *  由于不能存在多个HiltAndroidApp 需要在debug模式时设置 @HiltAndroidApp
 */

//@HiltAndroidApp
class MainDebugApplication : BaseApplication(){
}