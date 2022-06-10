package com.music.libase.net.exception

import com.google.gson.JsonParseException
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @Author leiwei
 * @Date   2022/5/11
 * @Desc   自定义ApiException
 */
class ApiException(var code: String, var errorMsg: String) : Exception() {

    companion object {
        const val UNKNOWN_ERROR = -0x10
        const val PARSE_ERROR = -0x11
        const val NETWORK_ERROR = -0x12

        /**
         * 处理网络请求异常
         *
         * @param e Throwable
         * @return ApiException
         */
        @JvmStatic
        fun handleException(e: Throwable): ApiException {
            e.printStackTrace()

            if (e is ApiException) {
                return e
            }
            return if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {
                ApiException(PARSE_ERROR.toString(), "数据解析异常")
            } else if (e is ConnectException
                || e is UnknownHostException
                || e is SocketTimeoutException
            ) {
                ApiException(NETWORK_ERROR.toString(), "网络请求异常")
            } else {
                ApiException(UNKNOWN_ERROR.toString(), "其他异常: ${e.message}")
            }
        }
    }

    override fun toString(): String {
        return "ApiException{code=${code},errorMsg=${errorMsg}}"
    }
}