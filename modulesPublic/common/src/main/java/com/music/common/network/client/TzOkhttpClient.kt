package com.music.common.network.client

import android.util.Log.VERBOSE
import com.blankj.utilcode.util.LogUtils
import com.music.common.network.intercept.TzLoginIntercept
import com.music.common.network.intercept.TzTokenIntercept
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import java.net.Proxy
import java.util.concurrent.TimeUnit

class TzOkhttpClient {

    companion object {

        private const val CONNECT_TIME = 15000L
        val client by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            val logging = HttpLoggingInterceptor {
                LogUtils.d(it)
            }
            logging.level = HttpLoggingInterceptor.Level.BODY

            return@lazy OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME, TimeUnit.MILLISECONDS)
                .readTimeout(CONNECT_TIME, TimeUnit.MILLISECONDS)
                .writeTimeout(CONNECT_TIME, TimeUnit.MILLISECONDS)
                .sslSocketFactory(
                    SSLSocketClient.getSSLSocketFactory(),
                    SSLSocketClient.TrustAllManager()
                )
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .addInterceptor(TzTokenIntercept())
                .addInterceptor(TzLoginIntercept())
                .addInterceptor(logging)
//                    LoggingInterceptor.Builder()
//                        .setLevel(if (BaseApp.app?.isDebug() == true) Level.BASIC else Level.NONE)
//                        .log(VERBOSE)
//                        .build()
//                )
                .proxy(Proxy.NO_PROXY)
                .build()
        }


    }
}