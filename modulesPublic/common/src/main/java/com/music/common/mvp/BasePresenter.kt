package com.music.common.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * @Author   leiwei
 * @Date     2022/5/17
 * @Desc     公用的presenter业务处理
 *           实现生命周期的管理
 */
open class BasePresenter<V : IView> : IPresenter<V>, LifecycleObserver {
    var mView: V? = null

    @JvmOverloads
    override fun attach(view: V) {
        mView = view
        //管理生命周期
        mView?.lifecycle?.addObserver(this)
    }

    override fun detach() {
        if (mView != null) {
            mView?.lifecycle?.removeObserver(this)
            mView = null
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        detach()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(owner: LifecycleOwner) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner) {

    }
}