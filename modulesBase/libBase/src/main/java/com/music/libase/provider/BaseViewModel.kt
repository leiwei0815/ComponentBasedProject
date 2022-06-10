package com.music.libase.provider

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.libase.api.PageState
import com.music.libase.api.ReportApi
import com.music.libase.api.network.NetworkApiKt

abstract class BaseViewModel : ViewModel(), PageStateProvider, ToastProvider, LoadingProvider,
    RouterProvider, ReportApi {

    override val pageState: MutableLiveData<PageState> by lazy {
        MutableLiveData(PageState.LOADING)
    }

    override val toastMsg: MutableLiveData<String?> by lazy {
        MutableLiveData()
    }

    override val isShowLoading: MutableLiveData<Boolean?> by lazy {
        MutableLiveData()
    }

    override val router: MutableLiveData<RouterProvider.RouteInfo> by lazy {
        MutableLiveData()
    }

}
