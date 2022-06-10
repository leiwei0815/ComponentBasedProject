package com.music.common.di

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * @Author   leiwei
 * @Date     2022/5/16
 * @Desc
 */
@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideRxPermissions(activity: Activity):RxPermissions{
        return RxPermissions(activity as FragmentActivity)
    }
}