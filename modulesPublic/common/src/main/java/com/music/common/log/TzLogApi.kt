package com.music.common.log

import com.music.libase.api.LogApi
import com.orhanobut.logger.Logger


interface TzLogApi : LogApi {

    override fun logd(vararg msg: Any) {
        Logger.d(createMessage(msg))
    }

    override fun loge(vararg msg: Any) {
        Logger.e(createMessage(msg))
    }

    override fun logi(vararg msg: Any) {
        Logger.i(createMessage(msg))
    }

    override fun logv(vararg msg: Any) {
        Logger.v(createMessage(msg))
    }

    override fun logw(vararg msg: Any) {
        Logger.w(createMessage(msg))
    }

    private fun createMessage(msg: Array<out Any>): String {
        val sb = StringBuffer()
        msg.forEach {
            sb.append(it)
            sb.appendLine()
        }
        return sb.toString()
    }
}