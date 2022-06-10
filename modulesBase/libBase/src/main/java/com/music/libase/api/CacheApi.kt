package com.music.libase.api
@JvmDefaultWithoutCompatibility
interface CacheApi {

    fun <T> putCache(key:String,value:T)

    fun <T> getCache(key:String):T?

    fun <T> getCache(key:String,default:T):T

    fun <T> popCache(key:String):T?

    fun removeCache(key:String)

    fun removeAllCache()

    fun containsKey(key:String):Boolean

    fun <T> containsValue(value:T):Boolean

    fun isEmpty():Boolean

}