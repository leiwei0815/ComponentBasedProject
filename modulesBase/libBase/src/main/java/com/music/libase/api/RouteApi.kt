package com.music.libase.api

/*
* 路由API
* */
@JvmDefaultWithoutCompatibility
interface RouteApi : CtxApi {

//    //需要实现跳转页面
//    fun <T:Activity> toActivity()
//    //需要实现跳转页面 带参
//    fun <T:Activity> toActivity(map:MutableMap<String,Any>)
//    //需要实现退出到某个页面
//    fun <T:Activity> popToActivity()
//    //需要实现替换当前页面
//    fun <T:Activity> replaceActivity()
//    //需要实现替换当前页面 带参
//    fun <T:Activity> replaceActivity(map:MutableMap<String,Any>)

    //需要实现用页面名称跳转
    fun toActivity(activity: String)

    //需要实现用页面名称跳转 带参
    fun toActivity(activity: String, map: MutableMap<String, Any>)

    //需要实跳转页面并返回参数
//    fun Fragment.toActivityForResult(activity:String,requestCode:Int,map:MutableMap<String,Any>? = null)
    fun toActivityForResult(activity: String, requestCode: Int, map: MutableMap<String, Any>? = null)

    //需要实现退出当前页面
//    fun Fragment.popActivity()
    fun popActivity()

    //需要实现退出当前页面并返回参数
//    fun Fragment.popActivityForResult(map:MutableMap<String,Any>,resultCode:Int)
    fun popActivityForResult(map: MutableMap<String, Any> = mutableMapOf(), resultCode: Int)

    //需要实现退出当前页面并返回参数
    fun popActivityForResult(resultCode: Int)

    //需要实现退出到首页
    fun popToMain()

    //需要实现退出到某个页面
    fun popToActivity(activity: String)

    //需要实现替换当前页面
    fun replaceActivity(activity: String)

    //需要实现替换当前页面 带参
    fun replaceActivity(activity: String, map: MutableMap<String, Any>)
}