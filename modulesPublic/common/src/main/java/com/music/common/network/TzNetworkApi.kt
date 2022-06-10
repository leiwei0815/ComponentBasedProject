package com.music.common.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.music.common.network.client.TzOkhttpClient
import com.music.libase.api.network.NetworkApiKt
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


interface TzNetworkApi : NetworkApiKt {

    override suspend fun get(
        url: String,
        params: Map<String, Any>?,
        headers: Map<String, String>?
    ): String {
        return withContext(Dispatchers.IO) {
            return@withContext requstMethod("GET", url, params, headers = headers)
        }
    }

    override suspend fun post(
        url: String,
        body: Any?,
        params: Map<String, Any>?,
        headers: Map<String, String>?
    ): String {
        return withContext(Dispatchers.IO) {
            return@withContext requstMethod("POST", url, params, body, headers)
        }
    }

    override suspend fun put(
        url: String,
        body: Any?,
        params: Map<String, Any>?,
        headers: Map<String, String>?
    ): String {
        return withContext(Dispatchers.IO) {
            return@withContext requstMethod("PUT", url, params, body, headers)
        }
    }

    override suspend fun delete(
        url: String,
        body: Any?,
        params: Map<String, Any>?,
        headers: Map<String, String>?
    ): String {
        return withContext(Dispatchers.IO) {
            return@withContext requstMethod("DELETE", url, params, body, headers)
        }
    }

    override suspend fun patch(
        url: String,
        body: Any?,
        params: Map<String, Any>?,
        headers: Map<String, String>?
    ): String {
        return withContext(Dispatchers.IO) {
            return@withContext requstMethod("PATCH", url, params, body, headers)
        }
    }

    override suspend fun head(
        url: String,
        params: Map<String, Any>?,
        headers: Map<String, String>?
    ): String {
        return withContext(Dispatchers.IO) {
            return@withContext requstMethod("HEAD", url, params, headers = headers)
        }
    }

    override suspend fun requst(
        method: String,
        url: String,
        params: Map<String, Any>?,
        body: Any?,
        headers: Map<String, String>?
    ): String {
        return withContext(Dispatchers.IO) {
            return@withContext requstMethod(method, url, params, body, headers)
        }
    }


    private suspend fun requstMethod(
        method: String,
        url: String,
        params: Map<String, Any>? = null,
        body: Any? = null,
        headers: Map<String, String>? = null
    ): String {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(params(url, params))
                .method(method, body(body)).apply {
                    headers?.forEach { (key, value) ->
                        header(key, value)
                    }
                }
                .build()
            val response = TzOkhttpClient.client.newCall(request).execute()
            if (response.isSuccessful) {
                return@withContext response.body?.string() ?: ""
            }
            cancel(HttpError(response.code.toString(), response.message))
            awaitCancellation()
        }
    }

    suspend fun download(url: String, params: Map<String, Any>?, body: Map<String, Any>?) {

    }

    private fun params(url: String, params: Map<String, Any>?): String {
        if (params == null) return url
        var path = "$url?"
        params.forEach { (key, value) ->
            path += "$key=$value&"
        }
        path = path.removeSuffix("&")
        return path
    }

    private fun body(body: Any?): RequestBody? {
        if (body == null) return null
        return when (body) {
            is Map<*, *> -> {
                val multipartBody = MultipartBody.Builder()
                body.forEach { (key, value) ->
                    when (value) {
                        is File -> {
                            val fileBody =
                                value.asRequestBody("application/octet-stream".toMediaTypeOrNull())
                            multipartBody.setType(MultipartBody.FORM)
                            multipartBody.addFormDataPart(key.toString(), value.name, fileBody)
                        }
                        else -> multipartBody.addFormDataPart(key.toString(), value.toString())
                    }
                }
                multipartBody.build()
            }

            is String -> {
                body.toRequestBody("text/plain".toMediaType())
            }
            else -> {
                body.toJson().toRequestBody("application/json".toMediaType())
            }
        }
    }

    companion object {
        fun init() {
            TzOkhttpClient.client
        }
    }
}

inline fun <reified T> String.toEntity(): T? {
    val type = object : TypeToken<T>() {}
    return Gson().fromJson(this, type.type)
}

inline fun <reified T> T.toJson(): String {
    val type = object : TypeToken<T>() {}
    return Gson().toJson(this, type.type)
}

class HttpError(val code: String, override val message: String) : CancellationException(message)
class NullDataError(val code: String, override val message: String) : CancellationException(message)