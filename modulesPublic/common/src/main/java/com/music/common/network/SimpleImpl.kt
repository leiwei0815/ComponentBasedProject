package com.tz.network.api

import com.music.common.network.TzNetworkApi

class SimpleImpl : TzNetworkApi {
//    override val router: MutableLiveData<RouterProvider.RouteInfo> = MutableLiveData(null)
    suspend fun test(){
        var res=get("", mapOf("" to ""))

    }
}