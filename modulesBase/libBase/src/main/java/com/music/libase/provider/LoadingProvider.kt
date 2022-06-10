package com.music.libase.provider

import androidx.lifecycle.MutableLiveData

interface LoadingProvider {

    val isShowLoading: MutableLiveData<Boolean?>

    fun showLoading() = isShowLoading.postValue(true)

    fun hideLoading() = isShowLoading.postValue(false)

}