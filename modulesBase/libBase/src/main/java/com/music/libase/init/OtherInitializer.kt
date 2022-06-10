package com.music.libase.init

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.blankj.utilcode.util.LogUtils
import kotlin.math.log

/**
 * @Author   leiwei
 * @Date     2022/5/12
 * @Desc
 */
class OtherInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        LogUtils.d("OtherInitializer : create")
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}