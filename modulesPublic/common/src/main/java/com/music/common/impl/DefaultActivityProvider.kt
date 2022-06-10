package com.music.common.impl

import androidx.lifecycle.MutableLiveData
import com.music.libase.api.PageState
import com.music.libase.provider.ActivityProvider
import com.music.libase.provider.BaseViewModel
import com.music.libase.provider.RouterProvider


interface DefaultActivityProvider<T:BaseViewModel>:ActivityProvider {
    val mViewModel:T
    override val router: MutableLiveData<RouterProvider.RouteInfo>
        get() = mViewModel.router
    override val pageState: MutableLiveData<PageState>
        get() = mViewModel.pageState
    override val toastMsg: MutableLiveData<String?>
        get() = mViewModel.toastMsg
    override val isShowLoading: MutableLiveData<Boolean?>
        get() = mViewModel.isShowLoading

}