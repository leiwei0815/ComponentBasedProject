package com.music.common.mvvm

import androidx.lifecycle.*
import com.blankj.utilcode.util.LogUtils

/**
 * @Author   leiwei
 * @Date     2022/5/18
 * @Desc
 */
open class VmBaseViewModel : ViewModel(),LifecycleObserver {

    lateinit var lifecycleOwner : LifecycleOwner


    fun attach(lifecycleOwner: LifecycleOwner){
        this.lifecycleOwner =lifecycleOwner
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onCleared() {
        super.onCleared()
        if (::lifecycleOwner.isInitialized){
            lifecycleOwner.lifecycle.removeObserver(this)
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        LogUtils.d("onDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(owner: LifecycleOwner) {
        LogUtils.d("onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner) {
        LogUtils.d("onResume")
    }
}