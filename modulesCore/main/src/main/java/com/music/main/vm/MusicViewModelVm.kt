package com.music.main.vm

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import com.music.common.mvvm.VmBaseViewModel
import com.music.common.net.ApiService
import com.music.libase.net.exception.ApiException
import com.music.libase.net.response.ICallback
import com.music.libase.net.response.RequestModel
import com.music.player.SearchData
import javax.inject.Inject

/**
 * @Author   leiwei
 * @Date     2022/5/18
 * @Desc @Inject
 */
open class MusicViewModelVm @Inject constructor(val apiService: ApiService, activity: Activity) :
    VmBaseViewModel() {

    init {
        attach(activity as ComponentActivity)
    }

    var musicData = MutableLiveData<SearchData>()

    fun loadMusics() {
        RequestModel.request(
            apiService.test("1", "10"),
            lifecycleOwner,
            object : ICallback<SearchData> {
                override fun onSuccess(data: SearchData) {
                    //如果是主线程那么直接用setValue
                    musicData.value = data

                    //musicDatas.postValue() 线程切换
                }

                override fun onFailure(e: ApiException) {

                }
            })
    }
}