package com.music.main.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.music.common.base.BaseFragment
import com.music.common.constant.RoutePath
import com.music.common.data.entity.UserInfo
import com.music.main.databinding.FragmentScheduleBinding
import com.music.main.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Route(path = RoutePath.MAIN_SCHEDULE)
@AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding>() {

//    @Inject
//    lateinit var userInfoViewModel: UserInfoViewModel

    override fun loadData() {
        super.loadData()
//        userInfoViewModel.loadUserInfo()

        //设置viewModel必须要设置lifecycleOwner
        binding.lifecycleOwner = this
//        binding.userInfo = userInfoViewModel

//        userInfoViewModel.userData.observe(this){
//            binding.userInfo = it
//        }
    }

}