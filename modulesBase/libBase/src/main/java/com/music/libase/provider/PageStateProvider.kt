package com.music.libase.provider

import androidx.lifecycle.MutableLiveData
import com.music.libase.api.PageState

interface PageStateProvider {

    val pageState: MutableLiveData<PageState>

    fun complete() {
        if (pageState.value != PageState.COMPLETE) pageState.postValue(PageState.COMPLETE)
    }

    fun fail() {
        if (pageState.value != PageState.FAIL) pageState.postValue(PageState.FAIL)
    }

    fun retry() {
        if (pageState.value != PageState.RETRY) pageState.postValue(PageState.RETRY)
    }

    fun loading() {
        if (pageState.value != PageState.LOADING) pageState.postValue(PageState.LOADING)
    }

}