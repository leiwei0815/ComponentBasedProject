package com.music.common.impl

data class TzBaseModel<T>(
    val status: String?,
    val code: String?,
    val msg: String?,
    val message: String?,
    val traceId: String,
    val userIdentitytraceId: String,
    val data: T
)

data class TzPagingModel<T>(
    val list: List<T>?,
    val pageIndex: Int,
    val pageSize: Int,
    val total: Int,
)