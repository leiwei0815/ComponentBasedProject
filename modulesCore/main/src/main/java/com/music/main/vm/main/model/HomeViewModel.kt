package com.music.main.vm.main.model

import com.music.common.TzViewModel
import com.music.common.impl.network
import com.music.main.vm.main.repository.HomeRepository

class HomeViewModel : TzViewModel() {

    private val mHomeRepository by lazy { HomeRepository() }

    fun initData() {
        complete()
        retry()
    }

}