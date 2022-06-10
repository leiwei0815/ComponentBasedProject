package com.music.common.impl

import androidx.lifecycle.MutableLiveData
import com.music.libase.api.PageState
import com.music.libase.provider.ActivityProvider
import com.music.libase.provider.RouterProvider


interface NoneActivityProvider:ActivityProvider {

    override val router: MutableLiveData<RouterProvider.RouteInfo>?
        get() = null
    override val pageState: MutableLiveData<PageState>?
        get() = null
    override val toastMsg: MutableLiveData<String?>?
        get() = null
    override val isShowLoading: MutableLiveData<Boolean?>?
        get() = null
}