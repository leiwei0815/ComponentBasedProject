package com.music.libase.api
@JvmDefaultWithoutCompatibility
interface SPApi{

     fun putSp(key:String,value:Any)

     fun <T> getSp(key:String,default:T):T

     fun removeSp(key:String)

     fun removeAllSp()

     fun hasSp(key:String):Boolean

     fun isSpEmpty():Boolean

}