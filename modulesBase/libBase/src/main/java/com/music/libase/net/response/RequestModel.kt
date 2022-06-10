package com.music.libase.net.response

import androidx.lifecycle.LifecycleOwner
import com.music.libase.net.exception.ApiException
import io.reactivex.Observable


/**
 * @Author   leiwei
 * @Date     2022/5/12
 * @Desc
 */
class RequestModel {

    companion object {
        fun <T> request(
            observable: Observable<out IResponse<T>>,
            lifecycleOwner: LifecycleOwner?,
            callback: ICallback<T>? = null
        ) {
            val d = observable.compose(lifecycleOwner?.let { ResponseTransformer.obtain(it) })
                .subscribe({ t ->
                    callback?.onSuccess(t)
                }, { throwable ->
                    callback?.onFailure(ApiException.handleException(throwable))
                })
        }
    }
}