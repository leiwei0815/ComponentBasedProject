package com.music.libase.api

import android.view.View
import androidx.annotation.LayoutRes

//主要附加生命周期
@JvmDefaultWithoutCompatibility
interface TzLifecycleApi {

    val topBarView: View?

    fun initData()

    fun bindLayoutId(): Int

    fun initView(view: View)

    fun onRetry()

}