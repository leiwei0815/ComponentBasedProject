package com.music.main.vm.model

import androidx.lifecycle.MutableLiveData
import com.music.common.TzViewModel
import com.music.main.vm.repository.MainRepository

class MainViewModel : TzViewModel() {

    private val mMainRepository by lazy { MainRepository() }
    val tabIndex by lazy { MutableLiveData(0) }

    fun initData() {
        complete()
    }

    fun changeTabIndex(index: Int) {
        tabIndex.postValue(index)
    }
}