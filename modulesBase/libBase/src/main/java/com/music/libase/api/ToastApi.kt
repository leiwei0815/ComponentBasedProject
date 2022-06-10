package com.music.libase.api

interface ToastApi :CtxApi{

    fun toast(msg: Any)

    fun globalToast(msg:Any)


    //fun postToast(msg:String)

}