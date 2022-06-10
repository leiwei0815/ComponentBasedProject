package com.music.libase.api

import android.view.View

interface PageStateApi :CtxApi{

    fun loadingView(): View

    fun errorView(): View

    fun retryView(): View

    fun onRetry()

}

enum class PageState {
    LOADING, COMPLETE, FAIL, RETRY
}