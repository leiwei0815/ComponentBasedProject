package com.music.common.impl

import androidx.lifecycle.viewModelScope
import com.music.common.TzViewModel
import com.music.common.network.HttpError
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.Closeable
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext


fun TzViewModel.network(call: suspend CoroutineScope.() -> Unit, onError: (code: String, msg: String) -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            call.invoke(viewModelScope)
        } catch (e: HttpError) {
            when (e.code) {
                Parameter.NOTLOGIN, Parameter.NOTLOGINCODE -> {
                    putSp(Parameter.TOKEN, "")
                }
                "404", "500" -> {
                    toastMsg("系统错误")
                }
                else -> toastMsg(e.message)
            }
            hideLoading()
            withContext(Dispatchers.Main) {
                onError.invoke(e.code, e.message)
            }
        } catch (e: UnknownHostException) {
            toastMsg("网络异常")
            hideLoading()
        } catch (e: SocketTimeoutException) {
            toastMsg("网络超时")
            hideLoading()
        } catch (e: Exception) {
            loge(e.message.toString())
            toastMsg("请求失败")
            hideLoading()
        }
    }
}

fun TzViewModel.networkWithRetry(
    call: suspend CoroutineScope.() -> Unit,
    onError: (code: String, msg: String) -> Unit
) {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            call.invoke(viewModelScope)
        } catch (e: HttpError) {
            when (e.code) {
                Parameter.NOTLOGIN, Parameter.NOTLOGINCODE -> {
                    putSp(Parameter.TOKEN, "")
                }
                "404", "500" -> {
                    toastMsg("系统错误")
                }
                else -> toastMsg(e.message)
            }
            hideLoading()
            if (e.code == "404" || e.code == "500") {
                toastMsg("系统错误")
                return@launch
            }
            withContext(Dispatchers.Main) {
                onError.invoke(e.code, e.message)
            }
        } catch (e: UnknownHostException) {
            logd("网络异常")
            retry()
            hideLoading()
        } catch (e: SocketTimeoutException) {
            logd("网络超时")
            retry()
            hideLoading()
        } catch (e: Exception) {
            retry()
            logd("请求失败")
            hideLoading()
        }
    }
}

fun TzViewModel.network(call: suspend NetworkManage.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val networkManage = NetworkManage(coroutineContext)
            call.invoke(networkManage)
        } catch (e: HttpError) {
            when (e.code) {
                Parameter.NOTLOGIN , Parameter.NOTLOGINCODE-> {
                    putSp(Parameter.TOKEN, "")
                }
                "404", "500" -> {
                    toastMsg("系统错误")
                }
                else -> toastMsg(e.message)
            }
            hideLoading()
        } catch (e: UnknownHostException) {
            loge(e.message.toString())
            hideLoading()
        } catch (e: SocketTimeoutException) {
            loge(e.message.toString())
            hideLoading()
        } catch (e: Exception) {
            loge(e.message.toString())
            hideLoading()
        }
    }
}


fun TzViewModel.networkAsync(call: suspend NetworkManage.() -> Unit): Deferred<Boolean> {
    return viewModelScope.async(Dispatchers.IO) {
        try {
            val networkManage = NetworkManage(coroutineContext)
            call.invoke(networkManage)
            return@async true
        } catch (e: HttpError) {
            when (e.code) {
                Parameter.NOTLOGIN, Parameter.NOTLOGINCODE -> {
                    putSp(Parameter.TOKEN, "")
                }
                "404", "500" -> {
                    toastMsg("系统错误")
                }
                else -> toastMsg(e.message)
            }
            hideLoading()
        } catch (e: UnknownHostException) {
            loge(e.message.toString())
            hideLoading()
        } catch (e: SocketTimeoutException) {
            loge(e.message.toString())
            hideLoading()
        } catch (e: Exception) {
            loge(e.message.toString())
            hideLoading()
        }
        return@async false
    }
}

suspend inline fun <reified T> TzBaseModel<T>.autoManage(): T {
    return withContext(Dispatchers.IO) {
        val isSuccess =
            code == Parameter.SUCCESSCODE || status == Parameter.SUCCESSCODE || code == Parameter.SUCCESSSTR
        if (!isSuccess) {
            cancel(HttpError(code ?: status ?: "ERROR", msg ?: message ?: "ERROR"))
            awaitCancellation()
        }
        handleIdentityChanged(userIdentitytraceId)
        return@withContext data
    }
}

fun handleIdentityChanged(userIdentity: String?): Boolean {
    return true
}


class NetworkManage(override val coroutineContext: CoroutineContext) : Closeable, CoroutineScope {
    //
    suspend fun <T> TzViewModel.safeCatch(call: suspend CoroutineScope.() -> T): T? {
        try {
            return call.invoke(this@NetworkManage)
        } catch (e: HttpError) {
            when (e.code) {
                Parameter.NOTLOGIN, Parameter.NOTLOGINCODE -> {
                    putSp(Parameter.TOKEN, "")
                }
                "404", "500" -> {
                    toastMsg("系统错误")
                }
                else -> toastMsg(e.message)
            }
            hideLoading()
        } catch (e: UnknownHostException) {
            loge(e.message.toString())
            hideLoading()
        } catch (e: SocketTimeoutException) {
            loge(e.message.toString())
            hideLoading()
        } catch (e: Exception) {
            loge(e.message.toString())
            hideLoading()
        }
        return null
    }

    //
    suspend fun <T> TzViewModel.tryCatch(call: suspend CoroutineScope.() -> T): Flow<T> {
        return flow {
            try {
                emit(call.invoke(this@NetworkManage))
            } catch (e: HttpError) {
                when (e.code) {
                    Parameter.NOTLOGIN, Parameter.NOTLOGINCODE -> {
                        putSp(Parameter.TOKEN, "")
                    }
                    "404", "500" -> {
                        toastMsg("系统错误")
                    }
                    else -> error(e.message)
                }
                hideLoading()
            } catch (e: UnknownHostException) {
                loge(e.message.toString())
                hideLoading()
            } catch (e: SocketTimeoutException) {
                loge(e.message.toString())
                hideLoading()
            } catch (e: Exception) {
                loge(e.message.toString())
                hideLoading()
            }
        }
    }

    fun <T> TzViewModel.igonreCatchAsync(call: suspend NetworkManage.() -> T): Deferred<T?> {
        return viewModelScope.async(Dispatchers.IO) {
            try {
                val networkManage = NetworkManage(coroutineContext)
                return@async call.invoke(networkManage)
            } catch (e: HttpError) {
                when (e.code) {
                    Parameter.NOTLOGIN, Parameter.NOTLOGINCODE -> {
                        putSp(Parameter.TOKEN, "")
                    }
                }
                hideLoading()
            } catch (e: UnknownHostException) {
                loge(e.message.toString())
                hideLoading()
            } catch (e: SocketTimeoutException) {
                loge(e.message.toString())
                hideLoading()
            } catch (e: Exception) {
                loge(e.message.toString())
                hideLoading()
            }
            return@async null
        }
    }

    fun <T> TzViewModel.safeCatchAsync(call: suspend NetworkManage.() -> T): Deferred<T?> {
        return viewModelScope.async(Dispatchers.IO) {
            try {
                val networkManage = NetworkManage(coroutineContext)
                return@async call.invoke(networkManage)
            } catch (e: HttpError) {
                when (e.code) {
                    Parameter.NOTLOGIN, Parameter.NOTLOGINCODE -> {
                        putSp(Parameter.TOKEN, "")
                    }
                    "404", "500" -> {
                        toastMsg("系统错误")
                    }
                    else -> toastMsg(e.message)
                }
                hideLoading()
            } catch (e: UnknownHostException) {
                loge(e.message.toString())
                hideLoading()
            } catch (e: SocketTimeoutException) {
                loge(e.message.toString())
                hideLoading()
            } catch (e: Exception) {
                loge(e.message.toString())
                hideLoading()
            }
            return@async null
        }
    }


    override fun close() {
        coroutineContext.cancel()
    }
}

inline fun <reified V> Any.cast(): V? {
    if (this is V) return this
    return null
}