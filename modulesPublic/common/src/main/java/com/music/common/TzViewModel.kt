package com.music.common

import androidx.lifecycle.MutableLiveData
import com.music.libase.provider.BaseViewModel
import com.music.common.log.TzLogApi
import com.music.common.storage.MMKVSPUtil

abstract class TzViewModel : BaseViewModel(), TzLogApi, MMKVSPUtil {
    val userObserver = MutableLiveData<Int>()
}