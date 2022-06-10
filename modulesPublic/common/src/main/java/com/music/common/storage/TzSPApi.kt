package com.music.common.storage

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.music.libase.api.SPApi
import com.music.libase.base.BaseApp

private val spEntity by lazy {
    BaseApp.app?.let { it.ctx.getSharedPreferences(it.getSPName(), Context.MODE_PRIVATE) }
}

interface TzSPApi : SPApi {

    override fun putSp(key: String, value: Any) {
        spEntity?.edit()?.apply { put(key, value) }?.apply()
    }


    override fun <T> getSp(key: String, default: T): T {
        return spEntity?.get(key, default) ?: default
    }

    @SuppressLint("ApplySharedPref")

    override fun removeSp(key: String) {
        spEntity?.edit()?.remove(key)?.apply()
    }

    @SuppressLint("ApplySharedPref")

    override fun removeAllSp() {
        spEntity?.edit()?.clear()?.apply()
    }


    override fun hasSp(key: String): Boolean {
        return spEntity?.contains(key) ?: false
    }


    override fun isSpEmpty(): Boolean {
        return spEntity?.all?.isEmpty() ?: true
    }


    private fun SharedPreferences.Editor.put(tag: String, obj: Any) {
        when (obj) {
            is String -> putString(tag, obj)
            is Long -> putLong(tag, obj)
            is Float -> putFloat(tag, obj)
            is Int -> putInt(tag, obj)
            is Boolean -> putBoolean(tag, obj)
            is Double -> putFloat(tag, obj.toFloat())
            else -> throw NotImplementedError("没有实现${obj::class.java.componentType}对象存储")
        }
    }

    private fun <T> SharedPreferences.get(tag: String, obj: T): T {
        return when (obj) {
            is String -> getString(tag, obj)
            is Long -> getLong(tag, obj)
            is Float -> getFloat(tag, obj)
            is Int -> getInt(tag, obj)
            is Boolean -> getBoolean(tag, obj)
            is Double -> getFloat(tag, obj.toFloat())
            else -> throw NotImplementedError("没有实现${obj}对象存储")
        } as T
    }

}