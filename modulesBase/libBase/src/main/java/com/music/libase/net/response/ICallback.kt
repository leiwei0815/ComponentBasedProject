package com.music.libase.net.response

import com.music.libase.net.exception.ApiException

/**
 * @Author   leiwei
 * @Date     2022/5/12
 * @Desc     回调接口
 */
interface ICallback<T> {

    fun onSuccess(data : T)

    fun onFailure(e:ApiException)
}