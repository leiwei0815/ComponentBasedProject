package com.music.common.net

import com.music.player.SearchData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author  leiwei
 * @Date    2022/5/12
 * @Desc
 */
interface ApiService {

    @GET("goods/search")
    fun test(
        @Query("pageIndex") pageIndex: String,
        @Query("pageSize") pageSize: String,
        @Query("terminalType") terminalType: String = "2"
    ): Observable<BaseResponse<SearchData>>
}