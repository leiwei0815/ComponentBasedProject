package com.music.libase.provider

import androidx.lifecycle.MutableLiveData

interface RouterProvider {
    class RouteInfo(
        val action: Action = Action.START,
        val name: String? = "",
        val params: MutableMap<String, Any> = mutableMapOf(),
        val code: Int = 0
    )

    val router: MutableLiveData<RouteInfo>


    //需要实现用页面名称跳转
    fun toActivity(activity: String) = router.postValue(RouteInfo(name = activity))

    //需要实现用页面名称跳转
    fun toActivityForResult(activity: String, requestCode: Int) =
        router.postValue(RouteInfo(name = activity, action = Action.START_RESULT, code = requestCode))

    //需要实现用页面名称跳转 带参
    fun toActivity(activity: String, map: MutableMap<String, Any>) =
        router.postValue(RouteInfo(name = activity, params = map))

    fun toActivityForResult(activity: String, requestCode: Int, map: MutableMap<String, Any>) =
        router.postValue(RouteInfo(name = activity, action = Action.START_RESULT, code = requestCode, params = map))

    //需要实现退出当前页面
    fun popActivity() = router.postValue(RouteInfo(action = Action.FINISH))

    //需要实现退出当前页面并返回参数
    fun popActivityForResult(resultCode:Int,map: MutableMap<String, Any> = mutableMapOf()) =
        router.postValue(RouteInfo(action = Action.RESULT, params = map,code = resultCode))

    //需要实现退出到首页
    fun popToMain() = router.postValue(RouteInfo(action = Action.MAIN))

    //需要实现退出到某个页面
    fun popToActivity(activity: String) =
        router.postValue(RouteInfo(action = Action.FINISH, name = activity))

    //需要实现替换当前页面
    fun replaceActivity(activity: String) =
        router.postValue(RouteInfo(action = Action.REPLACE, name = activity))

    //需要实现替换当前页面 带参
    fun replaceActivity(activity: String, map: MutableMap<String, Any>) =
        router.postValue(RouteInfo(action = Action.REPLACE, name = activity, params = map))


}


enum class Action {
    START, START_RESULT, FINISH, REPLACE, MAIN, RESULT
}