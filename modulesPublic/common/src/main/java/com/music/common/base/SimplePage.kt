package com.music.common.base

import com.music.common.constant.RoutePath

/**
 * 注册SimplePage相关fragment信息
 */
enum class SimplePage(val title:String,val path:String) {
    USER_INFO("用户信息",RoutePath.USER_INFO_FRAGMENT)
}