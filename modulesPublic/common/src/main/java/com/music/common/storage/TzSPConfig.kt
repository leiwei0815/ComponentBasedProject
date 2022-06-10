package com.music.common.storage


import android.content.Context
import com.music.libase.config.SPConfig
import com.tencent.mmkv.MMKV


interface TzSPConfig:SPConfig {

    override fun initSP(context: Context) {
        MMKV.initialize(context)
    }
}