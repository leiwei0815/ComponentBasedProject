package com.music.libase.net.response

import java.lang.StringBuilder

/**
 * @Author leiwei
 * @Date   2022/5/11
 * @Desc   具体的返回的类型数据不知道
 *         通过接口的形式来转换兼容
 */
interface IResponse<T> {

    fun getIData():T

    fun getIMsg():String

    fun getIMsgId():String

    fun getICode():String

    fun isSuccess():Boolean
}