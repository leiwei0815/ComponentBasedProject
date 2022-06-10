package com.music.libase.config

import android.content.Context

interface SPConfig {
    fun getSPName():String
    fun initSP(context: Context)
}