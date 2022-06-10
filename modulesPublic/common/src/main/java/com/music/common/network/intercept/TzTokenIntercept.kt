package com.music.common.network.intercept


import com.music.libase.base.BaseApp
import okhttp3.Interceptor
import okhttp3.Response

class TzTokenIntercept : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requst = chain.request()
        val reqBuild = requst.newBuilder().apply {
            if (requst.header("terminalType") == null)
                header("terminalType", "2")
            if (requst.header("token") == null)
                header("token", BaseApp.app?.getToken() ?: "")
            if (requst.header("clientVersion") == null)
                header("clientVersion", BaseApp.app?.getVersion() ?: "")
            if (requst.header("productId") == null)
                header("productId", "1013")
        }
        return chain.proceed(reqBuild.build())
    }

}