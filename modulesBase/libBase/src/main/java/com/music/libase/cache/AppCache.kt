package com.music.libase.cache

import com.blankj.utilcode.util.CacheMemoryStaticUtils
import com.blankj.utilcode.util.CacheMemoryUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.SPUtils

/**
 * @Author   leiwei
 * @Date     2022/5/16
 * @Desc     App频繁用到的文件管理和操作
 */
object AppCache {

    fun initAppDir() {
        val cache = CacheMemoryUtils.getInstance("music", 100)
        CacheMemoryStaticUtils.setDefaultCacheMemoryUtils(cache)
    }


    fun save(key: String, value: String) {
        CacheMemoryStaticUtils.put(key, value)
    }

    fun getString(key: String): String {
        return CacheMemoryStaticUtils.get(key)
    }

    fun remove(key: String) {
        CacheMemoryStaticUtils.remove(key)
    }
}