package com.music.common.utils

import com.alibaba.android.arouter.launcher.ARouter
import com.music.common.base.SimplePage
import com.music.common.constant.RoutePath

object PageHelper {

    fun showActivity(path: String){
        ARouter.getInstance().build(path).navigation()
    }

    /**
     * path : 加载fragment的path
     */
    fun showSimplePage(page: SimplePage){
        ARouter.getInstance().build(RoutePath.SIMPLE_PAGE_ACTIVITY)
            .withString("title",page.title)
            .withString("path",page.path)
            .navigation()
    }
}