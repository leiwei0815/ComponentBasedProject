package com.music.libase.utils

import android.content.Context
import java.io.IOException
import java.util.*

/**
 * @Author   leiwei
 * @Date     2022/5/12
 * @Desc     获取appConfig配置信息
 */
object PropertiesUtil {
    private var properties: Properties? = null

    fun init(context: Context){
        properties = Properties ()
        try {
            properties?.load(context.assets.open("appConfig.properties"))
        } catch ( e:IOException) {
            e.printStackTrace()
        }
    }

    // 获取值
    fun getValue(key: String): String {
        return properties?.getProperty(key) ?: ""
    }
}