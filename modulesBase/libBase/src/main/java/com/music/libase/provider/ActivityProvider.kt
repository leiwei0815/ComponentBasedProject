package com.music.libase.provider

import androidx.lifecycle.MutableLiveData
import com.music.libase.api.CtxApi
import com.music.libase.api.PageState

interface ActivityProvider :CtxApi{

    val router: MutableLiveData<RouterProvider.RouteInfo>?

    val pageState: MutableLiveData<PageState>?

    val toastMsg:MutableLiveData<String?>?

    val isShowLoading: MutableLiveData<Boolean?>?

}