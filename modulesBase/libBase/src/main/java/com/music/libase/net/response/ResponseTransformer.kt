package com.music.libase.net.response

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.music.libase.net.exception.ApiException
import com.music.libase.utils.ReflectUtil
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers


/**
 * @Author  leiwei
 * @Date    2022/5/11
 * @Desc    Observable<IResponse<T> -> Observable<T>
 *     实现:
 *     1. 对线程进行切换, 达到代码复用的目标
 *     2. 对RxJava声明周期管理, 防止内存泄漏
 *     3. 对响应数据统一处理, 获取到真正想用的data,进行业务处理
 */
class ResponseTransformer<T> : ObservableTransformer<IResponse<T>, T>, LifecycleObserver {

    companion object {
        fun <T> obtain(lifecycleOwner: LifecycleOwner):ResponseTransformer<T>{
            val responseTransformer = ResponseTransformer<T>()
            lifecycleOwner.lifecycle.addObserver(responseTransformer)
            return responseTransformer
        }
    }


    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

    override fun apply(upstream: Observable<IResponse<T>>): ObservableSource<T> {

        return upstream.doOnSubscribe {
            compositeDisposable.add(it)
        }.onErrorResumeNext(Function<Throwable, ObservableSource<out IResponse<T>>> { t ->
            //出现异常统一处理(非业务性的异常)
            Observable.error(ApiException.handleException(t))
        }).flatMap(Function<IResponse<T>, ObservableSource<T>> { response ->
            //对响应数据统一处理
            if (response.isSuccess()) {
                if (response.getIData() != null) {
                    return@Function Observable.just(response.getIData())
                } else {
                    //业务请求可能成功了, 但是data是null
                    //通过反射手动创建data,这个data一般是没有实际用途
                    val clz = ReflectUtil.analysisClassInfo(response)
                    val objectInfo = clz.newInstance() as T
                    return@Function Observable.just(objectInfo)
                }
            }
            return@Function Observable.error(ApiException(response.getICode(), response.getIMsg()))
        }).subscribeOn(Schedulers.io())//指定事件产生的线程
            .observeOn(AndroidSchedulers.mainThread())//指定事件处理的线程

    }
}