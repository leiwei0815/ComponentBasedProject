package com.music.main.vm

import androidx.lifecycle.MutableLiveData
import com.music.common.mvvm.VmBaseViewModel
import javax.inject.Inject


class MainViewModelVm @Inject constructor():VmBaseViewModel() {

    val tabIndex by lazy { MutableLiveData(0) }

    fun changeTabIndex(index: Int) {
        tabIndex.postValue(index)
    }
}