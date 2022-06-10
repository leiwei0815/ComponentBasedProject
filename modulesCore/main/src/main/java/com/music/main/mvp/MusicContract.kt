package com.music.main.mvp

import com.music.common.mvp.IPresenter
import com.music.common.mvp.IView
import com.music.player.CourseBrandItemModel

/**
 * @Author   leiwei
 * @Date     2022/5/17
 * @Desc
 */
public interface MusicContract {

    /**
     * 业务相关处理
     */
    interface View : IView {
        fun onLoadMusicSuccess(list: List<CourseBrandItemModel>)

        fun onLoadMusicFailure(msg: String)
    }

    interface Presenter : IPresenter<View> {
        fun loadMusics(page: Int)
    }
}