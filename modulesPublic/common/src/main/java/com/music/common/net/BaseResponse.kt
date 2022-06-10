package com.music.common.net

import com.music.libase.net.response.IResponse

/**
 * @Author   leiwei
 * @Date     2022/5/12
 * @Desc
 */
class BaseResponse<T>(
    val data: T,
    val code: String,
    val msg: String,
    val messageId: String
) : IResponse<T> {
    override fun getIData(): T {
       return data
    }

    override fun getIMsg(): String {
        return msg
    }

    override fun getIMsgId(): String {
        return messageId
    }

    override fun getICode(): String {
        return code
    }

    override fun isSuccess(): Boolean {
        return "200" == code || "0".equals(code)
    }
}