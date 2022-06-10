package com.music.libase.api.network

interface NetworkApi {

    fun <T> requst(clazz: Class<T>): T

}

interface NetworKCallback {

    fun onSuceese()

    fun onLogicError()

    fun onSystemError()

}

interface NetworkApiKt {
    suspend fun get(
        url: String,
        params: Map<String, Any>? = null,
        headers: Map<String, String>? = null

    ): String

    suspend fun post(
        url: String,
        body: Any? = null,
        params: Map<String, Any>? = null,
        headers: Map<String, String>? = null
    ): String

    suspend fun put(
        url: String,
        body: Any? = null,
        params: Map<String, Any>? = null,
        headers: Map<String, String>? = null
    ): String

    suspend fun delete(
        url: String,
        body: Any? = null,
        params: Map<String, Any>?= null,
        headers: Map<String, String>?= null

    ): String

    suspend fun patch(
        url: String,
        body: Any? = null,
        params: Map<String, Any>?= null,
        headers: Map<String, String>?= null
    ): String

    suspend fun head(
        url: String,
        params: Map<String, Any>?= null,
        headers: Map<String, String>?= null
    ): String

    suspend fun requst(
        method:String,
        url: String,
        params: Map<String, Any>? = null,
        body: Any? = null,
        headers: Map<String, String>?= null
    ): String
}