package com.music.libase.provider

import androidx.lifecycle.MutableLiveData

interface ToastProvider {

    val toastMsg:MutableLiveData<String?>

    fun toastMsg(msg: String) = toastMsg.postValue(msg)

}