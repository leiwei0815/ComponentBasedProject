package com.music.libase.api

interface LogApi {

    fun logi(vararg msg:Any)

    fun logd(vararg msg:Any)

    fun loge(vararg msg:Any)

    fun logw(vararg msg:Any)

    fun logv(vararg msg:Any)

}