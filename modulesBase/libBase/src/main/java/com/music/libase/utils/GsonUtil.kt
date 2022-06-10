package com.music.libase.utils

import com.google.gson.Gson
import java.lang.ref.WeakReference

/**
 * @Author   leiwei
 * @Date     2022/5/12
 * @Desc     使用WeakReference处理gson, 避免重复创建
 */
object GsonUtil {
    private var instance: WeakReference<Gson?>? = null

    fun get(): Gson {
        if (instance == null || instance!!.get() == null) {
            val gson = Gson()
            instance = WeakReference(gson)
        }
        return instance?.get() ?: Gson()
    }
}