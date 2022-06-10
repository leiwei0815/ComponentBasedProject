package com.music.common.mvp

import androidx.lifecycle.LifecycleOwner

/**
 * @Author   leiwei
 * @Date     2022/5/17
 * @Desc     基础的view接口,定义了公共的UI处理方法, 并继承LifecycleOwner
 */
open interface IView : LifecycleOwner{
    fun showLoading(msg : String)

    fun dismissLoading()

    fun showToast(msg :String)

    fun showLoadingLayout()

    fun showSuccessLayout()

    fun showErrorLayout()

    fun showEmptyLayout()
}