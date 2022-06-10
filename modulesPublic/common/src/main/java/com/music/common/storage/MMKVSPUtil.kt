package com.music.common.storage

import android.os.Parcelable
import com.music.libase.api.SPApi
import com.music.libase.base.BaseApp
import com.tencent.mmkv.MMKV
import java.util.*

/**
 * @auther 子非猿
 * @date 2021/09/02
 * @Description 数据存取MMKV
 */
private val mmkv by lazy {
    BaseApp.app?.let {
        MMKV.mmkvWithID("InterProcessKV", MMKV.MULTI_PROCESS_MODE) }
}

interface MMKVSPUtil : SPApi {

    override fun putSp(key: String, value: Any) {
        put(key,value)
    }

    override fun <T> getSp(key: String, default: T): T {
        return get(key,default)
    }

    override fun removeSp(key: String) {
        removeKey(key)
    }

    override fun removeAllSp() {
        clearAll()
    }

    override fun hasSp(key: String): Boolean {
        return mmkv?.containsKey(key)?:false
    }

    override fun isSpEmpty(): Boolean {
        return if (mmkv == null) true
        else{
            mmkv!!.actualSize() <= 0L
        }
    }

    private fun put(key: String, value: Any?) {
        when (value) {
            is String -> mmkv?.encode(key, value)
            is Float -> mmkv?.encode(key, value)
            is Boolean -> mmkv?.encode(key, value)
            is Int -> mmkv?.encode(key, value)
            is Long -> mmkv?.encode(key, value)
            is Double -> mmkv?.encode(key, value)
            is ByteArray -> mmkv?.encode(key, value)
            is Nothing -> return
        }
    }

    fun<T> get(key: String ,defValue:T):T{
        return  when(defValue){
            is String -> decodeString(key,defValue)
            is Float -> decodeFloat(key,defValue)
            is Boolean -> decodeBoolean(key,defValue)
            is Int -> decodeInt(key,defValue)
            is Long -> decodeLong(key,defValue)
            is Double -> decodeDouble(key,defValue)
            is ByteArray -> decodeByteArray(key)?:defValue
            else ->defValue
        } as T
    }

    fun <T : Parcelable> encode(key: String, t: T?) {
        if(t ==null){
            return
        }
        mmkv?.encode(key, t)
    }

    fun encode(key: String, sets: Set<String>?) {
        if(sets ==null){
            return
        }
        mmkv?.encode(key, sets)
    }

    private fun decodeInt(key: String,value:Int): Int? {
        return mmkv?.decodeInt(key, value)
    }

    private fun decodeDouble(key: String,value:Double): Double? {
        return mmkv?.decodeDouble(key, value)
    }

    private fun decodeLong(key: String,value:Long): Long? {
        return mmkv?.decodeLong(key, value)
    }

    private fun decodeBoolean(key: String,value:Boolean): Boolean? {
        return mmkv?.decodeBool(key, value)
    }

    private fun decodeFloat(key: String,value:Float): Float? {
        return mmkv?.decodeFloat(key, value)
    }

    private fun decodeByteArray(key: String): ByteArray? {
        return mmkv?.decodeBytes(key)
    }

    private fun decodeString(key: String,value:String): String? {
        return mmkv?.decodeString(key, value)
    }

    fun <T : Parcelable> decodeParcelable(key: String, tClass: Class<T>): T? {
        return mmkv?.decodeParcelable(key, tClass)
    }

    fun decodeStringSet(key: String): Set<String>? {
        return mmkv?.decodeStringSet(key, Collections.emptySet())
    }

    private fun removeKey(key: String) {
        mmkv?.removeValueForKey(key)
    }


    private fun clearAll() {
        mmkv?.clearAll()
    }


}
