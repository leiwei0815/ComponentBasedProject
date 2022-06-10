package com.music.main.mvp

import androidx.fragment.app.Fragment
import com.music.common.mvp.BasePresenter
import com.music.common.net.ApiService
import com.music.libase.net.exception.ApiException
import com.music.libase.net.response.ICallback
import com.music.libase.net.response.RequestModel
import com.music.player.SearchData
import javax.inject.Inject

/**
 * @Author   leiwei
 * @Date     2022/5/17
 * @Desc
 */
class MusicPresenter @Inject constructor(private val apiService: ApiService,fragment: Fragment) :
    BasePresenter<MusicContract.View>(), MusicContract.Presenter {

    init {
        attach(fragment as MusicContract.View)
    }

    override fun loadMusics(page: Int) {
        RequestModel.request(apiService.test("1", "10"), mView, object : ICallback<SearchData> {
            override fun onSuccess(data: SearchData) {
                mView?.onLoadMusicSuccess(data.list)
            }

            override fun onFailure(e: ApiException) {
                mView?.onLoadMusicFailure(e.errorMsg)
            }
        })
    }
}