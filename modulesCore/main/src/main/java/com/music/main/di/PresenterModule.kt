package com.music.main.di

import androidx.fragment.app.Fragment
import com.music.common.net.ApiService
import com.music.main.mvp.MusicContract
import com.music.main.mvp.MusicPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class PresenterModule {

    //注入Presenter, 约定的规范: 所有的Presenter只注入到Fragment中
//    @Provides
//    fun provideMusicPresenter(fragment: Fragment,apiService: ApiService): MusicPresenter {
//        val view = fragment as MusicContract.View
//        val musicPresenter = MusicPresenter(apiService,fragment)
//        musicPresenter.attach(view)
//        return musicPresenter
//    }
}