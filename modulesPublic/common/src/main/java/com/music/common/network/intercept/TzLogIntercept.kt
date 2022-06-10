package com.music.common.network.intercept

import okhttp3.Interceptor
import okhttp3.Response

class TzLogIntercept:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}