package com.music.main.vm

import com.alibaba.android.arouter.facade.annotation.Route
import com.music.common.base.BaseFragment
import com.music.common.constant.RoutePath
import com.music.main.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Route(path = RoutePath.USER_INFO_FRAGMENT)
@AndroidEntryPoint
class UserInfoFragment : BaseFragment<FragmentUserBinding>() {

    @Inject
    lateinit var userInfoViewModel: UserInfoViewModelVm

    override fun loadData() {
        super.loadData()
        userInfoViewModel.loadUserInfo()

        //设置viewModel必须要设置lifecycleOwner
        binding.lifecycleOwner = this
        binding.userInfo = userInfoViewModel

//        userInfoViewModel.userData.observe(this){
//            binding.userInfo = it
//        }
    }

}