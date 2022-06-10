package com.music.common.mvp

import android.view.View

/**
 * @Author   leiwei
 * @Date     2022/5/17
 * @Desc     基础的presenter接口, 定义attach进行view关联,detach方法回收销毁资源
 */
open interface IPresenter<V> {

    fun attach(view: V)

    fun detach()
}