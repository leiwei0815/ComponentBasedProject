package com.music.main.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class ViewModelModule {

//    @Provides
//    fun provideMusicViewModel(activity: Activity):MusicViewModel{
////        ViewModelProvider(this).get(MusicViewModel::class.java)
//        val viewModel = ViewModelProvider(activity as ComponentActivity).get(MusicViewModel::class.java)
//        viewModel.attach(activity)
//        return viewModel
//    }
}