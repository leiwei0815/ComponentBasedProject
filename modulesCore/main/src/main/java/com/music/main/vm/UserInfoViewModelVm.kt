package com.music.main.vm

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import com.music.common.data.entity.UserInfo
import com.music.common.mvvm.VmBaseViewModel
import com.music.common.net.ApiService
import com.music.libase.net.exception.ApiException
import com.music.libase.net.response.ICallback
import com.music.libase.net.response.RequestModel
import com.music.player.SearchData
import javax.inject.Inject

class UserInfoViewModelVm @Inject constructor(val apiService: ApiService, activity: Activity) :
    VmBaseViewModel() {

    init {
        attach(activity as ComponentActivity)
    }

    val userData = MutableLiveData<UserInfo>()

    fun loadUserInfo() {
        RequestModel.request(
            apiService.test("1", "10"),
            lifecycleOwner,
            object : ICallback<SearchData> {
                override fun onSuccess(data: SearchData) {
                    userData.value = UserInfo()
                }

                override fun onFailure(e: ApiException) {

                }
            })
    }
}