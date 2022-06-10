package com.music.common.impl


import com.music.common.log.TzLogApi
import com.music.common.network.toEntity
import com.music.common.network.toJson
import com.music.common.R
import com.music.common.item.NoDataModel
import com.music.common.item.MoreDataModel
import com.music.common.item.NoMoreModel

class QuickPagingHelper(
    val request: suspend (pageIndex: Int, pageSize: Int) -> String,
    val convert: (data: String) -> Any,
    var size: Int = 10,
    var index: Int = 1,
) : TzLogApi {
    val contentList = mutableListOf<Any>()

    suspend fun reload(imgRes: Int = R.drawable.empty_bg, hintStr: String = "什么都没有~", call: (List<Any>) -> Unit) {
        try {
            index = 1
            val res =
                request(index, size).toEntity<TzBaseModel<TzPagingModel<Any>>>()?.autoManage() ?: return call(listOf())
            contentList.clear()
            res.list?.forEach {
                contentList.add(convert(it.toJson()))
            }
            when {
                contentList.isEmpty() -> {
                    contentList.add(NoDataModel(hintStr, imgRes))
                }
                contentList.size < size -> {
                    contentList.add(NoMoreModel())
                }
                contentList.size == size -> {
                    contentList.add(MoreDataModel { loadMore(call) })
                }
            }
        } catch (e: Exception) {
            contentList.clear()
            contentList.add(NoDataModel())
            loge("QuickPagingHelper : $e")
        }
        call(contentList.toList())

    }

    private suspend fun loadMore(call: (List<Any>) -> Unit) {
        try {
            val res =
                request(++index, size).toEntity<TzBaseModel<TzPagingModel<Any>>>()?.autoManage()
                    ?: return call(listOf())
            val list = mutableListOf<Any>()
            res.list?.forEach {
                list.add(convert(it.toJson()))
            }
            contentList.removeAll { it is MoreDataModel || it is NoMoreModel }
            contentList.addAll(list)
            when {
                list.size < size -> {
                    contentList.add(NoMoreModel())
                }
                list.size == size -> {
                    contentList.add(MoreDataModel { loadMore(call) })
                }
            }
            call(contentList.toList())
        } catch (e: Exception) {
            loge(e.message ?: "")
        }
    }
}